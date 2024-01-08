package com.ljnewmap.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {
	
}
