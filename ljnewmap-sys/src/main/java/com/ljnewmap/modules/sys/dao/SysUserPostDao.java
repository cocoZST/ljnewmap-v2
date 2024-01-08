package com.ljnewmap.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.sys.entity.SysUserPostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserPostDao extends BaseMapper<SysUserPostEntity> {
    void deleteByPostIds(Long[] postIds);

    void deleteByUserIds(Long[] userIds);

    List<SysUserPostEntity> getUserPostList(Long userId);
}