package com.ljnewmap.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.log.entity.SysLogErrorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 异常日志
 *
 */
@Mapper
public interface SysLogErrorDao extends BaseMapper<SysLogErrorEntity> {
	
}
