package com.ljnewmap.modules.sys.dto;

import com.ljnewmap.common.validator.group.DefaultGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 用户岗位关系
 */
@Data
@Schema(description = "用户岗位关系")
public class SysUserPostDTO {
    private static final long serialVersionUID = 1L;

    @Schema(description = "岗位id")
    @NotNull(message="ID不能为空", groups = DefaultGroup.class)
    private Long postId;

    @Schema(description = "任职类型: 0: 全职 1: 兼职 2: 实习", requiredMode = Schema.RequiredMode.REQUIRED)
    @Range(min=0, max=3, message = "{sysuserpost.status.range}", groups = DefaultGroup.class)
    private Integer status;
}
