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

@Data
@Schema(description = "岗位实体")
public class SysPostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @Null(message="ID必须为空", groups = AddGroup.class)
    @NotNull(message="ID不能为空", groups = UpdateGroup.class)
    private Long id;

    @Schema(description = "岗位编码")
    @NotBlank(message="岗位编码不能为空", groups = DefaultGroup.class)
    private String code;

    @Schema(description = "岗位名称")
    @NotBlank(message="岗位名称不能为空", groups = DefaultGroup.class)
    private String name;

    @Schema(description = "状态（0:停用 1:正常）")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @Schema(description = "排序")
    @Min(value = 0, message = "排序值不能小于0", groups = DefaultGroup.class)
    private Integer sort;
}
