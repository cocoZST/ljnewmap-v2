package com.ljnewmap.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.exception.RRException;
import com.ljnewmap.common.exception.SysErrorCodeConstants;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.common.utils.TreeUtils;
import com.ljnewmap.modules.security.user.UserDetail;
import com.ljnewmap.modules.sys.dao.SysMenuDao;
import com.ljnewmap.modules.sys.dto.SysMenuResDTO;
import com.ljnewmap.modules.sys.dto.SysMenuTreeDTO;
import com.ljnewmap.modules.sys.dto.SysMenuReqDTO;
import com.ljnewmap.modules.sys.entity.SysMenuEntity;
import com.ljnewmap.modules.sys.enums.SuperAdminEnum;
import com.ljnewmap.modules.sys.service.SysRoleMenuService;
import com.ljnewmap.modules.sys.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public SysMenuResDTO get(Long id) {
        SysMenuEntity entity = baseMapper.getById(id);

        SysMenuResDTO dto = ConvertUtils.sourceToTarget(entity, SysMenuResDTO.class);

        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysMenuReqDTO dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

        //保存菜单
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenuReqDTO dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

        //上级菜单不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new RRException(SysErrorCodeConstants.MENU_SUPERIOR_ERROR);
        }

        //更新菜单
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //删除菜单
        baseMapper.deleteById(id);

        //删除角色菜单关系
        sysRoleMenuService.deleteByMenuId(id);
    }

    @Override
    public List<SysMenuTreeDTO> getAllMenuList(Integer menuType) {
        List<SysMenuEntity> menuList = baseMapper.getMenuList(menuType);

        List<SysMenuTreeDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuTreeDTO.class);

        return TreeUtils.build(dtoList, Constant.MENU_ROOT);
    }

    @Override
    public List<SysMenuTreeDTO> getUserMenuList(UserDetail user, Integer menuType) {
        List<SysMenuEntity> menuList;

        //系统管理员，拥有最高权限
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            menuList = baseMapper.getMenuList(menuType);
        } else {
            menuList = baseMapper.getUserMenuList(user.getId(), menuType);
        }

        List<SysMenuTreeDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuTreeDTO.class);

        return TreeUtils.build(dtoList);
    }

    @Override
    public List<SysMenuTreeDTO> getListPid(Long pid) {
        List<SysMenuEntity> menuList = baseMapper.getListPid(pid);

        return ConvertUtils.sourceToTarget(menuList, SysMenuTreeDTO.class);
    }

}