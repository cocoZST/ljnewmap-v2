package com.ljnewmap.modules.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.modules.security.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	RT createToken(Long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(Long userId);

}