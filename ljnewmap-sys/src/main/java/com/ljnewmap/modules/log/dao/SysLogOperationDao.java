package com.ljnewmap.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.log.entity.SysLogOperationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 *
 */
@Mapper
public interface SysLogOperationDao extends BaseMapper<SysLogOperationEntity> {
	
}
