package com.ljnewmap.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.common.page.Query;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.modules.security.user.SecurityUser;
import com.ljnewmap.modules.security.user.UserDetail;
import com.ljnewmap.modules.sys.dto.SysPostDTO;
import com.ljnewmap.modules.sys.entity.SysPostEntity;
import com.ljnewmap.modules.sys.enums.SuperAdminEnum;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.modules.sys.dao.SysPostDao;
import com.ljnewmap.modules.sys.service.SysPostService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostDao, SysPostEntity> implements SysPostService{

    @Override
    public PageData<SysPostDTO> page(Map<String, Object> params) {
        IPage<SysPostEntity> page = baseMapper.selectPage(
                new Query<SysPostEntity>().getPage(params, Constant.CREATE_DATE,false),
                getWrapper(params)
        );
        return new PageData<>(page,SysPostDTO.class);
    }

    private QueryWrapper<SysPostEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");
        String code = (String) params.get("code");
        Integer status = (Integer) params.get("status");

        QueryWrapper<SysPostEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(name), "name", name);
        wrapper.like(StrUtil.isNotBlank(code), "code", code);
        wrapper.eq(status != null, "status", status);

        //普通管理员，只能查询创建自己创建的
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            wrapper.eq("creator", user.getId());
        }

        return wrapper;
    }

    @Override
    public List<SysPostDTO> list(Map<String, Object> params) {
        List<SysPostEntity> entityList = baseMapper.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList,SysPostDTO.class);
    }

    @Override
    public SysPostDTO get(Long id) {
        SysPostEntity sysPostEntity = baseMapper.selectById(id);

        return ConvertUtils.sourceToTarget(sysPostEntity,SysPostDTO.class);
    }

    @Override
    public void save(SysPostDTO dto) {
        SysPostEntity entity = ConvertUtils.sourceToTarget(dto, SysPostEntity.class);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysPostDTO dto) {
        SysPostEntity entity = ConvertUtils.sourceToTarget(dto, SysPostEntity.class);

        updateById(entity);
    }

    @Override
    public void delete(Long[] ids) {
        baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
