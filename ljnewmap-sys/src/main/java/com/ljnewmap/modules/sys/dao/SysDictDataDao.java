package com.ljnewmap.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.sys.entity.DictData;
import com.ljnewmap.modules.sys.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据
 *
 */
@Mapper
public interface SysDictDataDao extends BaseMapper<SysDictDataEntity> {

    /**
     * 字典数据列表
    */
    List<DictData> getDictDataList();
}
