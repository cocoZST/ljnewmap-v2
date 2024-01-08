package com.ljnewmap.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.Query;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.modules.log.dao.SysLogOperationDao;
import com.ljnewmap.modules.log.dto.SysLogOperationDTO;
import com.ljnewmap.modules.log.entity.SysLogOperationEntity;
import com.ljnewmap.modules.log.service.SysLogOperationService;
import com.ljnewmap.common.page.PageData;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 */
@Service
public class SysLogOperationServiceImpl extends ServiceImpl<SysLogOperationDao, SysLogOperationEntity> implements SysLogOperationService {

    @Override
    public PageData<SysLogOperationDTO> page(Map<String, Object> params) {
        IPage<SysLogOperationEntity> page = baseMapper.selectPage(
            new Query<SysLogOperationEntity>().getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return new PageData(page, SysLogOperationDTO.class);
    }

    @Override
    public List<SysLogOperationDTO> list(Map<String, Object> params) {
        List<SysLogOperationEntity> entityList = baseMapper.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogOperationDTO.class);
    }

    private QueryWrapper<SysLogOperationEntity> getWrapper(Map<String, Object> params){
        Integer status = (Integer) params.get("status");

        QueryWrapper<SysLogOperationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(status != null, "status", status);

        return wrapper;
    }

}