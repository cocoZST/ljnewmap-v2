package com.ljnewmap.modules.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.Query;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.modules.job.dao.ScheduleJobLogDao;
import com.ljnewmap.modules.job.dto.ScheduleJobLogDTO;
import com.ljnewmap.modules.job.entity.ScheduleJobLogEntity;
import com.ljnewmap.modules.job.service.ScheduleJobLogService;
import com.ljnewmap.common.page.PageData;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

	@Override
	public PageData<ScheduleJobLogDTO> page(Map<String, Object> params) {
		IPage<ScheduleJobLogEntity> page = baseMapper.selectPage(
			new Query<ScheduleJobLogEntity>().getPage(params, Constant.CREATE_DATE, false),
			getWrapper(params)
		);
		return new PageData(page, ScheduleJobLogDTO.class);
	}

	private QueryWrapper<ScheduleJobLogEntity> getWrapper(Map<String, Object> params){
		Long jobId = (Long)params.get("jobId");

		QueryWrapper<ScheduleJobLogEntity> wrapper = new QueryWrapper<>();
		wrapper.eq(jobId != null, "job_id", jobId);

		return wrapper;
	}

	@Override
	public ScheduleJobLogDTO get(Long id) {
		ScheduleJobLogEntity entity = baseMapper.selectById(id);

		return ConvertUtils.sourceToTarget(entity, ScheduleJobLogDTO.class);
	}

}