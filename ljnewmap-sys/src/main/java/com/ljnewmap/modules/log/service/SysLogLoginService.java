package com.ljnewmap.modules.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.log.dto.SysLogLoginDTO;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.modules.log.entity.SysLogLoginEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 */
public interface SysLogLoginService extends IService<SysLogLoginEntity> {

    PageData<SysLogLoginDTO> page(Map<String, Object> params);

    List<SysLogLoginDTO> list(Map<String, Object> params);

}