package com.ljnewmap.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljnewmap.common.entity.BaseAllEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数管理
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_params")
public class SysParamsEntity extends BaseAllEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 参数编码
	 */
	private String paramCode;
	/**
	 * 参数值
	 */
	private String paramValue;
	/**
	 * 类型   0：系统参数   1：非系统参数
	 */
	private Integer paramType;
	/**
	 * 备注
	 */
	private String remark;

}