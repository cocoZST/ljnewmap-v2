package com.ljnewmap.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.common.page.Query;
import com.ljnewmap.modules.log.dto.SysLogErrorDTO;
import com.ljnewmap.modules.log.entity.SysLogErrorEntity;
import com.ljnewmap.modules.oss.dao.OssDao;
import com.ljnewmap.modules.oss.entity.OssEntity;
import com.ljnewmap.modules.oss.service.OssService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OssServiceImpl extends ServiceImpl<OssDao, OssEntity> implements OssService {

    @Override
    public PageData<OssEntity> page(Map<String, Object> params) {
        IPage<OssEntity> page = baseMapper.selectPage(
                new Query<OssEntity>().getPage(params, Constant.CREATE_DATE, false),
                getWrapper(params)
        );
        return new PageData(page, OssEntity.class);
    }

    private QueryWrapper<OssEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<OssEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }
}
