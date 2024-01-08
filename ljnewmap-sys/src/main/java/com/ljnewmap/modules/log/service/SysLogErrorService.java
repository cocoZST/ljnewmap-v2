package com.ljnewmap.modules.log.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.log.dto.SysLogErrorDTO;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.modules.log.entity.SysLogErrorEntity;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 */
public interface SysLogErrorService extends IService<SysLogErrorEntity> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

}