package com.ljnewmap.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.log.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 */
@Mapper
public interface SysLogLoginDao extends BaseMapper<SysLogLoginEntity> {
	
}
