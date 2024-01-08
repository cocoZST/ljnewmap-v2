package com.ljnewmap.modules.job.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 */
@Data
@Schema(description = "定时任务日志")
public class ScheduleJobLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "任务id")
    private Long jobId;

    @Schema(description = "spring bean名称")
    private String beanName;

    @Schema(description = "参数")
    private String params;

    @Schema(description = "任务状态    0：失败    1：成功")
    private Integer status;

    @Schema(description = "失败信息")
    private String error;

    @Schema(description = "耗时(单位：毫秒)")
    private Integer times;

    @Schema(description = "创建时间")
    private Date createDate;

}
