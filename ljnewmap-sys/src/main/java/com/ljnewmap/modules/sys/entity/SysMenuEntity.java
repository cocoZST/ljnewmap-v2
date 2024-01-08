package com.ljnewmap.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljnewmap.common.entity.BaseAllEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 菜单管理
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_menu")
public class SysMenuEntity extends BaseAllEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 父菜单ID，一级菜单为0
	 */
	private Long pid;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单URL
	 */
	private String url;
	/**
	 * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
	 */
	private String permissions;
	/**
	 * 类型   0：菜单   1：按钮
	 */
	private Integer menuType;
	/**
	 * 菜单图标
	 */
	private String iconName;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 是否可见  false：不可见  true：可见
	 */
	private Boolean visible;
	/**
	 * 上级菜单名称
	 */
	@TableField(exist = false)
	private String parentName;

}