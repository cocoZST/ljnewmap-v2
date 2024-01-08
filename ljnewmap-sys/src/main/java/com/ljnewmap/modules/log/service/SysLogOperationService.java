package com.ljnewmap.modules.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.log.dto.SysLogOperationDTO;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.modules.log.entity.SysLogOperationEntity;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 */
public interface SysLogOperationService extends IService<SysLogOperationEntity> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);
}