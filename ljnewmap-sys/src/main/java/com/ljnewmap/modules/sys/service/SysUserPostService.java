package com.ljnewmap.modules.sys.service;

import com.ljnewmap.modules.sys.dto.SysUserPostDTO;
import com.ljnewmap.modules.sys.entity.SysUserPostEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysUserPostService extends IService<SysUserPostEntity>{

    /**
     * 保存或修改
     * @param userId    用户id
     * @param userPostList  用户岗位关系列表
     */
    void saveOrUpdate(Long userId, List<SysUserPostDTO> userPostList);

    /**
     * 根据岗位ids,删除用户岗位关系
     * @param postIds   岗位ids
     */
    void deleteByPostIds(Long[] postIds);

    /**
     * 根据用户ids,删除用户岗位关系
     * @param userIds   用户ids
     */
    void deleteByUserIds(Long[] userIds);

    /**
     * 根据用户id获取岗位列表
     * @param userId    用户id
     * @return
     */
    List<SysUserPostDTO> getUserPostList(Long userId);
}
