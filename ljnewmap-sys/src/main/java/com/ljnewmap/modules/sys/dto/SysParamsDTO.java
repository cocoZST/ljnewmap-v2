package com.ljnewmap.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 参数管理
 *
 */
@Data
@Schema(description = "参数管理")
public class SysParamsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @Null(message="ID必须为空", groups = AddGroup.class)
    @NotNull(message="ID不能为空", groups = UpdateGroup.class)
    private Long id;

    @Schema(description = "参数编码")
    @NotBlank(message="参数编码不能为空", groups = DefaultGroup.class)
    private String paramCode;

    @Schema(description = "参数值")
    @NotBlank(message="参数值不能为空", groups = DefaultGroup.class)
    private String paramValue;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @Schema(description = "更新时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateDate;

}
