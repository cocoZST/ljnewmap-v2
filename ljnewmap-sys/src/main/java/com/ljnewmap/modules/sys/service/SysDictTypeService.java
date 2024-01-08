package com.ljnewmap.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.sys.dto.SysDictTypeDTO;
import com.ljnewmap.modules.sys.entity.DictType;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.modules.sys.entity.SysDictTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 */
public interface SysDictTypeService extends IService<SysDictTypeEntity> {

    PageData<SysDictTypeDTO> page(Map<String, Object> params);

    SysDictTypeDTO get(Long id);

    void save(SysDictTypeDTO dto);

    void update(SysDictTypeDTO dto);

    void delete(Long[] ids);

    /**
     * 获取所有字典
    */
    List<DictType> getAllList();

}