package com.ljnewmap.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.oss.dto.DataCommonDTO;
import com.ljnewmap.modules.oss.entity.DataCommonEntity;

public interface DataCommonService extends IService<DataCommonEntity> {

    void upload(DataCommonDTO dataCommonDTO);
}
