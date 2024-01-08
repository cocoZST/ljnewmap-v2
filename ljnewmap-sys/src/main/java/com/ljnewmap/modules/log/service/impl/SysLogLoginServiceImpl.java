package com.ljnewmap.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.Query;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.modules.log.dao.SysLogLoginDao;
import com.ljnewmap.modules.log.dto.SysLogLoginDTO;
import com.ljnewmap.modules.log.entity.SysLogLoginEntity;
import com.ljnewmap.modules.log.service.SysLogLoginService;
import com.ljnewmap.common.page.PageData;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 */
@Service
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginDao, SysLogLoginEntity> implements SysLogLoginService {

    @Override
    public PageData<SysLogLoginDTO> page(Map<String, Object> params) {
        IPage<SysLogLoginEntity> page = baseMapper.selectPage(
            new Query<SysLogLoginEntity>().getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return new PageData(page, SysLogLoginDTO.class);
    }

    @Override
    public List<SysLogLoginDTO> list(Map<String, Object> params) {
        List<SysLogLoginEntity> entityList = baseMapper.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogLoginDTO.class);
    }

    private QueryWrapper<SysLogLoginEntity> getWrapper(Map<String, Object> params){
        Integer status = (Integer) params.get("status");
        String creatorName = (String) params.get("creatorName");

        QueryWrapper<SysLogLoginEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(status != null, "status", status);
        wrapper.like(StrUtil.isNotBlank(creatorName), "creator_name", creatorName);

        return wrapper;
    }

}