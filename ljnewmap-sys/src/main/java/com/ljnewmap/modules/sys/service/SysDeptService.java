package com.ljnewmap.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljnewmap.modules.sys.dto.SysDeptReqDTO;
import com.ljnewmap.modules.sys.dto.SysDeptResDTO;
import com.ljnewmap.modules.sys.dto.SysDeptTreeDTO;
import com.ljnewmap.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 */
public interface SysDeptService extends IService<SysDeptEntity> {

	List<SysDeptTreeDTO> list(Map<String, Object> params);

	SysDeptResDTO get(Long id);

	void save(SysDeptReqDTO dto);

	void update(SysDeptReqDTO dto);

	void delete(Long id);

	/**
	 * 根据部门ID，获取本部门及子部门ID列表
	 * @param id   部门ID
	 */
	List<Long> getSubDeptIdList(Long id);
}