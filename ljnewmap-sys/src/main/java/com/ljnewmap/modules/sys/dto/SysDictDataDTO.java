package com.ljnewmap.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
 * 字典数据
 *
 */
@Data
@Schema(description = "字典数据")
public class SysDictDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	@Null(message="ID必须为空", groups = AddGroup.class)
	@NotNull(message="ID不能为空", groups = UpdateGroup.class)
	private Long id;

	@Schema(description = "字典类型ID")
	@NotNull(message="字典类型不能为空", groups = DefaultGroup.class)
	private Long dictTypeId;

	@Schema(description = "字典标签")
	@NotBlank(message="字典标签不能为空", groups = DefaultGroup.class)
	private String dictLabel;

	@Schema(description = "字典值")
	private String dictValue;

	@Schema(description = "备注")
	private String remark;

	@Schema(description = "排序")
	@Min(value = 0, message = "排序值不能小于0", groups = DefaultGroup.class)
	private Integer sort;

	@Schema(description = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(description = "更新时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date updateDate;
}