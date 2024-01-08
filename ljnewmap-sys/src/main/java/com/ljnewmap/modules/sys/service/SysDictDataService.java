package com.ljnewmap.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.sys.dto.SysDictDataDTO;
import com.ljnewmap.modules.sys.entity.SysDictDataEntity;
import com.ljnewmap.common.page.PageData;

import java.util.Map;

/**
 * 数据字典
 *
 */
public interface SysDictDataService extends IService<SysDictDataEntity> {

    PageData<SysDictDataDTO> page(Map<String, Object> params);

    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    void delete(Long[] ids);

}