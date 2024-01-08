package com.ljnewmap.modules.sys.controller;

import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.common.validator.AssertUtils;
import com.ljnewmap.common.validator.ValidatorUtils;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import com.ljnewmap.modules.sys.dto.SysPostDTO;
import com.ljnewmap.modules.sys.service.SysPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/post")
@Tag(name = "岗位管理")
@RequiredArgsConstructor
public class SysPostController {
    private final SysPostService sysPostService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "code", description = "岗位编码", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "name", description = "岗位名称", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "status", description = "状态（0正常 1停用）", in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    @RequiresPermissions("sys:post:page")
    public RT<PageData<SysPostDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysPostDTO> page = sysPostService.page(params);

        return new RT<PageData<SysPostDTO>>().ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermissions("sys:post:list")
    public RT<List<SysPostDTO>> list() {
        List<SysPostDTO> data = sysPostService.list(new HashMap<>(1));

        return new RT<List<SysPostDTO>>().ok(data);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:post:info")
    public RT<SysPostDTO> get(@PathVariable("id") Long id) {
        SysPostDTO data = sysPostService.get(id);

        return new RT<SysPostDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:post:save")
    public RT save(@RequestBody SysPostDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysPostService.save(dto);

        return new RT();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:post:update")
    public RT update(@RequestBody SysPostDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysPostService.update(dto);

        return new RT();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:post:delete")
    public RT delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysPostService.delete(ids);

        return new RT();
    }
}
