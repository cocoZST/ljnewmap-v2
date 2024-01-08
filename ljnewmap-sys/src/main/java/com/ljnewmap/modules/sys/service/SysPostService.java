package com.ljnewmap.modules.sys.service;

import com.ljnewmap.common.page.PageData;
import com.ljnewmap.modules.sys.dto.SysPostDTO;
import com.ljnewmap.modules.sys.entity.SysPostEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface SysPostService extends IService<SysPostEntity>{

    PageData<SysPostDTO> page(Map<String, Object> params);

    List<SysPostDTO> list(Map<String, Object> params);

    SysPostDTO get(Long id);

    void save(SysPostDTO dto);

    void update(SysPostDTO dto);

    void delete(Long[] ids);
}
