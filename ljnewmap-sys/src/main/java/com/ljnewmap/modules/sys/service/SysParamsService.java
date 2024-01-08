package com.ljnewmap.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.modules.sys.dto.SysParamsDTO;
import com.ljnewmap.modules.sys.entity.SysParamsEntity;

import java.util.List;
import java.util.Map;

/**
 * 参数管理
 *
 */
public interface SysParamsService extends IService<SysParamsEntity> {

    PageData<SysParamsDTO> page(Map<String, Object> params);

    List<SysParamsDTO> list(Map<String, Object> params);

    SysParamsDTO get(Long id);

    void save(SysParamsDTO dto);

    void update(SysParamsDTO dto);

    void delete(Long[] ids);

    /**
     * 根据参数编码，获取参数的value值
     *
     * @param paramCode  参数编码
    */
    String getValue(String paramCode);

    /**
     * 根据参数编码，获取value的Object对象
     * @param paramCode  参数编码
     * @param clazz  Object对象
    */
    <T> T getValueObject(String paramCode, Class<T> clazz);

    /**
     * 根据参数编码，更新value
     * @param paramCode  参数编码
     * @param paramValue  参数值
    */
    int updateValueByCode(String paramCode, String paramValue);
}
