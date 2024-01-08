package com.ljnewmap.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljnewmap.modules.sys.entity.SysRoleUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色用户关系
 *
 */
@Mapper
public interface SysRoleUserDao extends BaseMapper<SysRoleUserEntity> {

    /**
     * 根据角色ids，删除角色用户关系
     * @param roleIds 角色ids
    */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * 根据用户id，删除角色用户关系
     * @param userIds 用户ids
    */
    void deleteByUserIds(Long[] userIds);

    /**
     * 角色ID列表
     * @param userId  用户ID
     *
     * @return
    */
    List<Long> getRoleIdList(Long userId);
}