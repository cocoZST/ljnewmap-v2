package com.ljnewmap.modules.job.controller;

import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.common.validator.ValidatorUtils;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import com.ljnewmap.modules.job.dto.ScheduleJobDTO;
import com.ljnewmap.modules.job.service.ScheduleJobService;
import com.ljnewmap.common.page.PageData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 */
@RestController
@RequestMapping("/sys/schedule")
@Tag(name = "定时任务")
@RequiredArgsConstructor
public class ScheduleJobController {

    private final ScheduleJobService scheduleJobService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in =  ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in =  ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in =  ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in =  ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "beanName", description = "beanName", in =  ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @RequiresPermissions("sys:schedule:page")
    public RT<PageData<ScheduleJobDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<ScheduleJobDTO> page = scheduleJobService.page(params);

        return new RT<PageData<ScheduleJobDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:schedule:info")
    public RT<ScheduleJobDTO> info(@PathVariable("id") Long id) {
        ScheduleJobDTO schedule = scheduleJobService.get(id);

        return new RT<ScheduleJobDTO>().ok(schedule);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:schedule:save")
    public RT save(@RequestBody ScheduleJobDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        scheduleJobService.save(dto);

        return new RT();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:schedule:update")
    public RT update(@RequestBody ScheduleJobDTO dto) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        scheduleJobService.update(dto);

        return new RT();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:schedule:delete")
    public RT delete(@RequestBody Long[] ids) {
        scheduleJobService.deleteBatch(ids);

        return new RT();
    }

    @PutMapping("/run")
    @Operation(summary = "立即执行")
    @LogOperation("立即执行")
    @RequiresPermissions("sys:schedule:run")
    public RT run(@RequestBody Long[] ids) {
        scheduleJobService.run(ids);

        return new RT();
    }

    @PutMapping("/pause")
    @Operation(summary = "暂停")
    @LogOperation("暂停")
    @RequiresPermissions("sys:schedule:pause")
    public RT pause(@RequestBody Long[] ids) {
        scheduleJobService.pause(ids);

        return new RT();
    }

    @PutMapping("/resume")
    @Operation(summary = "恢复")
    @LogOperation("恢复")
    @RequiresPermissions("sys:schedule:resume")
    public RT resume(@RequestBody Long[] ids) {
        scheduleJobService.resume(ids);

        return new RT();
    }

}