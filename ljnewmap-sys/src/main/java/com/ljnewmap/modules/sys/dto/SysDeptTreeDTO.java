package com.ljnewmap.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljnewmap.common.utils.TreeNode;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门管理
 *
 */
@Data
@Schema(description = "部门管理(树结构)")
public class SysDeptTreeDTO extends TreeNode implements Serializable {
    private static final long serialVersionUID = 1L;

//	@Schema(description = "id")
//	@Null(message="ID必须为空", groups = AddGroup.class)
//	@NotNull(message="ID不能为空", groups = UpdateGroup.class)
//	private Long id;
//
//	@Schema(description = "上级ID")
//	@NotNull(message="请选择上级部门", groups = DefaultGroup.class)
//	private Long pid;

	@Schema(description = "部门名称")
	@NotBlank(message="部门名称不能为空", groups = DefaultGroup.class)
	private String name;

	@Schema(description = "排序")
	@Min(value = 0, message = "排序值不能小于0", groups = DefaultGroup.class)
	private Integer sort;

	@Schema(description = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(description = "上级部门名称")
	private String parentName;
}