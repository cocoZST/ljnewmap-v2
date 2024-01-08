package com.ljnewmap.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.job.dto.ScheduleJobLogDTO;
import com.ljnewmap.modules.job.entity.ScheduleJobLogEntity;
import com.ljnewmap.common.page.PageData;

import java.util.Map;

/**
 * 定时任务日志
 *
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageData<ScheduleJobLogDTO> page(Map<String, Object> params);

	ScheduleJobLogDTO get(Long id);
}
