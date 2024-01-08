package com.ljnewmap.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljnewmap.common.utils.TreeNode;
import com.ljnewmap.common.validator.group.DefaultGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单管理
 *
 */
@Data
@Schema(description = "菜单管理(树结构)")
public class SysMenuTreeDTO extends TreeNode<SysMenuTreeDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

//	@Schema(description = "id")
//	@Null(message="ID必须为空", groups = AddGroup.class)
//	@NotNull(message="ID不能为空", groups = UpdateGroup.class)
//	private Long id;
//
//	@Schema(description = "上级ID")
//	@NotNull(message="请选择上级菜单", groups = DefaultGroup.class)
//	private Long pid;

	@Schema(description = "菜单名称")
	@NotBlank(message="菜单名称不能为空", groups = DefaultGroup.class)
	private String name;

	@Schema(description = "菜单URL")
	private String url;

	@Schema(description = "类型  0：菜单   1：按钮")
	@Range(min=0, max=1, message = "菜单类型取值范围0~1", groups = DefaultGroup.class)
	private Integer menuType;

	@Schema(description = "菜单图标")
	private String iconName;

	@Schema(description = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
	private String permissions;

	@Schema(description = "排序")
	@Min(value = 0, message = "排序值不能小于0", groups = DefaultGroup.class)
	private Integer sort;

	@Schema(description = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(description = "是否可见  false：不可见  true：可见")
	@NotNull(message="是否可见不能为空", groups = DefaultGroup.class)
	private Boolean visible;

	@Schema(description = "上级菜单名称")
	private String parentName;
}