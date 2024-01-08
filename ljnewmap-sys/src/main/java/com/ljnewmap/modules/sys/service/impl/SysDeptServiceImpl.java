package com.ljnewmap.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.exception.RRException;
import com.ljnewmap.common.exception.SysErrorCodeConstants;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.common.utils.TreeUtils;
import com.ljnewmap.modules.security.user.SecurityUser;
import com.ljnewmap.modules.security.user.UserDetail;
import com.ljnewmap.modules.sys.dao.SysDeptDao;
import com.ljnewmap.modules.sys.dao.SysUserDao;
import com.ljnewmap.modules.sys.dto.SysDeptReqDTO;
import com.ljnewmap.modules.sys.dto.SysDeptResDTO;
import com.ljnewmap.modules.sys.dto.SysDeptTreeDTO;
import com.ljnewmap.modules.sys.entity.SysDeptEntity;
import com.ljnewmap.modules.sys.enums.SuperAdminEnum;
import com.ljnewmap.modules.sys.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {
    private final SysUserDao sysUserDao;

    @Override
    public List<SysDeptTreeDTO> list(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", getSubDeptIdList(user.getDeptId()));
        }

        //查询部门列表
        List<SysDeptEntity> entityList = baseMapper.getList(params);

        List<SysDeptTreeDTO> dtoList = ConvertUtils.sourceToTarget(entityList, SysDeptTreeDTO.class);

        return TreeUtils.build(dtoList);
    }

    @Override
    public SysDeptResDTO get(Long id) {
        //超级管理员，部门ID为null
        if (id == null) {
            return null;
        }

        SysDeptEntity entity = baseMapper.getById(id);

        return ConvertUtils.sourceToTarget(entity, SysDeptResDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDeptReqDTO dto) {
        SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

        entity.setPids(getPidList(entity.getPid()));
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDeptReqDTO dto) {
        SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

        //上级部门不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new RRException(SysErrorCodeConstants.DEPT_SUPERIOR_ERROR);
        }

        //上级部门不能为下级部门
        List<Long> subDeptList = getSubDeptIdList(entity.getId());
        if (subDeptList.contains(entity.getPid())) {
            throw new RRException(SysErrorCodeConstants.DEPT_SUPERIOR_ERROR);
        }

        entity.setPids(getPidList(entity.getPid()));
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //判断是否有子部门
        List<Long> subList = getSubDeptIdList(id);
        if (subList.size() > 1) {
            throw new RRException(SysErrorCodeConstants.DEPT_SUB_DELETE_ERROR);
        }

        //判断部门下面是否有用户
        int count = sysUserDao.getCountByDeptId(id);
        if (count > 0) {
            throw new RRException(SysErrorCodeConstants.DEPT_USER_DELETE_ERROR);
        }

        //删除
        baseMapper.deleteById(id);
    }

    @Override
    public List<Long> getSubDeptIdList(Long id) {
        List<Long> deptIdList = baseMapper.getSubDeptIdList("%" + id + "%");
        deptIdList.add(id);

        return deptIdList;
    }

    /**
     * 获取所有上级部门ID
     *
     * @param pid 上级ID
    */
    private String getPidList(Long pid) {
        //顶级部门，无上级部门
        if (Constant.DEPT_ROOT.equals(pid)) {
            return Constant.DEPT_ROOT + "";
        }

        //所有部门的id、pid列表
        List<SysDeptEntity> deptList = baseMapper.getIdAndPidList();

        //list转map
        Map<Long, SysDeptEntity> map = new HashMap<>(deptList.size());
        for (SysDeptEntity entity : deptList) {
            map.put(entity.getId(), entity);
        }

        //递归查询所有上级部门ID列表
        List<Long> pidList = new ArrayList<>();
        getPidTree(pid, map, pidList);

        return StringUtils.join(pidList, ",");
    }

    private void getPidTree(Long pid, Map<Long, SysDeptEntity> map, List<Long> pidList) {
        //顶级部门，无上级部门
        if (Constant.DEPT_ROOT.equals(pid)) {
            return;
        }

        //上级部门存在
        SysDeptEntity parent = map.get(pid);
        if (parent != null) {
            getPidTree(parent.getPid(), map, pidList);
        }

        pidList.add(pid);
    }
}
