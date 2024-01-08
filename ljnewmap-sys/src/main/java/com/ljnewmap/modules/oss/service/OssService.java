package com.ljnewmap.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.modules.oss.entity.OssEntity;

import java.util.Map;

public interface OssService extends IService<OssEntity> {

    PageData<OssEntity> page(Map<String, Object> params);
}
