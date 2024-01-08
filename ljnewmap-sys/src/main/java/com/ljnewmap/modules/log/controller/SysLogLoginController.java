package com.ljnewmap.modules.log.controller;

import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.utils.ExcelUtils;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.modules.log.dto.SysLogLoginDTO;
import com.ljnewmap.modules.log.excel.SysLogLoginExcel;
import com.ljnewmap.modules.log.service.SysLogLoginService;
import com.ljnewmap.common.page.PageData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 登录日志
 *
 */
@RestController
@RequestMapping("sys/log/login")
@Tag(name = "登录日志")
@RequiredArgsConstructor
public class SysLogLoginController {
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "status", description = "状态  0：失败    1：成功    2：账号已锁定", in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
            @Parameter(name = "creatorName", description = "用户名", in = ParameterIn.QUERY,  schema = @Schema(type = "string"))
    })
    @RequiresPermissions("sys:log:login")
    public RT<PageData<SysLogLoginDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params)  {
        PageData<SysLogLoginDTO> page = sysLogLoginService.page(params);

        return new RT<PageData<SysLogLoginDTO>>().ok(page);
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:log:login")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysLogLoginDTO> list = sysLogLoginService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "登录日志", list, SysLogLoginExcel.class);

    }

}