package com.ljnewmap.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 */
@Data
@Schema(description = "用户管理")
public class SysUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	@Null(message="ID必须为空", groups = AddGroup.class)
	@NotNull(message="ID不能为空", groups = UpdateGroup.class)
	private Long id;

	@Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message="用户名不能为空", groups = DefaultGroup.class)
	private String username;

	@Schema(description = "密码")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	private String password;

	@Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message="姓名不能为空", groups = DefaultGroup.class)
	private String realName;

	@Schema(description = "头像")
	private String headUrl;

	@Schema(description = "性别   0：男   1：女    2：保密", requiredMode = Schema.RequiredMode.REQUIRED)
	@Range(min=0, max=2, message = "性别取值范围0~2", groups = DefaultGroup.class)
	private Integer gender;

	@Schema(description = "邮箱")
	@Email(message="邮箱格式不正确", groups = DefaultGroup.class)
	private String email;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull(message="部门不能为空", groups = DefaultGroup.class)
	private Long deptId;

	@Schema(description = "状态  0：停用    1：正常", requiredMode = Schema.RequiredMode.REQUIRED)
	@Range(min=0, max=1, message = "状态取值范围0~1", groups = DefaultGroup.class)
	private Integer status;

	@Schema(description = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(description = "超级管理员   0：否   1：是")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer superAdmin;

	@Schema(description = "角色ID列表")
	private List<Long> roleIdList;

	@Schema(description = "部门名称")
	private String deptName;

	@Schema(description = "岗位列表")
	private List<SysUserPostDTO> userPostList;

}