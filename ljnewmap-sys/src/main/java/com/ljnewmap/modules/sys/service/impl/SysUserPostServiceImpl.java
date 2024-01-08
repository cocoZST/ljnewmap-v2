package com.ljnewmap.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.modules.sys.dto.SysUserPostDTO;
import com.ljnewmap.modules.sys.entity.SysUserPostEntity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.modules.sys.dao.SysUserPostDao;
import com.ljnewmap.modules.sys.service.SysUserPostService;

import java.util.List;

@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostDao, SysUserPostEntity> implements SysUserPostService{

    @Override
    public void saveOrUpdate(Long userId, List<SysUserPostDTO> userPostList) {
        //先删除用户岗位关系
        deleteByUserIds(new Long[]{userId});

        //用户没有一个角色权限的情况
        if(CollUtil.isEmpty(userPostList)){
            return ;
        }

        for(SysUserPostDTO dto:userPostList){
            SysUserPostEntity sysUserPostEntity = new SysUserPostEntity();
            sysUserPostEntity.setUserId(userId);
            sysUserPostEntity.setPostId(dto.getPostId());
            sysUserPostEntity.setStatus(dto.getStatus());

            //保存
            baseMapper.insert(sysUserPostEntity);
        }
    }

    @Override
    public void deleteByPostIds(Long[] postIds) {
        baseMapper.deleteByPostIds(postIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        baseMapper.deleteByUserIds(userIds);
    }

    @Override
    public List<SysUserPostDTO> getUserPostList(Long userId) {
        List<SysUserPostEntity> list = baseMapper.getUserPostList(userId);
        return ConvertUtils.sourceToTarget(list, SysUserPostDTO.class);
    }
}
