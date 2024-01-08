package com.ljnewmap.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.Query;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.modules.log.dao.SysLogErrorDao;
import com.ljnewmap.modules.log.dto.SysLogErrorDTO;
import com.ljnewmap.modules.log.entity.SysLogErrorEntity;
import com.ljnewmap.modules.log.service.SysLogErrorService;
import com.ljnewmap.common.page.PageData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 */
@Service
public class SysLogErrorServiceImpl extends ServiceImpl<SysLogErrorDao, SysLogErrorEntity> implements SysLogErrorService {

    @Override
    public PageData<SysLogErrorDTO> page(Map<String, Object> params) {
        IPage<SysLogErrorEntity> page = baseMapper.selectPage(
            new Query<SysLogErrorEntity>().getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return new PageData(page, SysLogErrorDTO.class);
    }

    @Override
    public List<SysLogErrorDTO> list(Map<String, Object> params) {
        List<SysLogErrorEntity> entityList = baseMapper.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogErrorDTO.class);
    }

    private QueryWrapper<SysLogErrorEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<SysLogErrorEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

}