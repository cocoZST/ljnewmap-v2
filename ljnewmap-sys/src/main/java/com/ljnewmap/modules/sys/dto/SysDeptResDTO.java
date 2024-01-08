package com.ljnewmap.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "部门管理(响应)")
public class SysDeptResDTO extends SysDeptBaseDTO{

    @Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @Schema(description = "上级部门名称")
    private String parentName;
}
