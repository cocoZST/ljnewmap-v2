package com.ljnewmap.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.sys.entity.DictType;
import com.ljnewmap.modules.sys.entity.SysDictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型
 *
 */
@Mapper
public interface SysDictTypeDao extends BaseMapper<SysDictTypeEntity> {

    /**
     * 字典类型列表
    */
    List<DictType> getDictTypeList();

}
