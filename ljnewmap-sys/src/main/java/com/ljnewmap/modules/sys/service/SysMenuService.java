package com.ljnewmap.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.security.user.UserDetail;
import com.ljnewmap.modules.sys.dto.SysMenuResDTO;
import com.ljnewmap.modules.sys.dto.SysMenuTreeDTO;
import com.ljnewmap.modules.sys.dto.SysMenuReqDTO;
import com.ljnewmap.modules.sys.entity.SysMenuEntity;

import java.util.List;


/**
 * 菜单管理
 * 
 */
public interface SysMenuService extends IService<SysMenuEntity> {

	SysMenuResDTO get(Long id);

	void save(SysMenuReqDTO dto);

	void update(SysMenuReqDTO dto);

	void delete(Long id);

	/**
	 * 菜单列表
	 *
	 * @param menuType 菜单类型
	 */
	List<SysMenuTreeDTO> getAllMenuList(Integer menuType);

	/**
	 * 用户菜单列表
	 *
	 * @param user  用户
	 * @param menuType 菜单类型
	 */
	List<SysMenuTreeDTO> getUserMenuList(UserDetail user, Integer menuType);

	/**
	 * 根据父菜单，查询子菜单
	 * @param pid  父菜单ID
	 */
	List<SysMenuTreeDTO> getListPid(Long pid);
}
