package com.ljnewmap.modules.job.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 */
@Data
@Schema(description = "定时任务")
public class ScheduleJobDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @Null(message="ID必须为空", groups = AddGroup.class)
    @NotNull(message="ID不能为空", groups = UpdateGroup.class)
    private Long id;

    @Schema(description = "spring bean名称")
    @NotBlank(message = "bean名称不能为空", groups = DefaultGroup.class)
    private String beanName;

    @Schema(description = "参数")
    private String params;

    @Schema(description = "cron表达式")
    @NotBlank(message = "cron表达式不能为空", groups = DefaultGroup.class)
    private String cronExpression;

    @Schema(description = "任务状态  0：暂停  1：正常")
    @Range(min=0, max=1, message = "状态取值范围0~1", groups = DefaultGroup.class)
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

}
