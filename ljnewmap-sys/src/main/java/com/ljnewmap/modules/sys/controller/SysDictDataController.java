package com.ljnewmap.modules.sys.controller;

import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.common.validator.AssertUtils;
import com.ljnewmap.common.validator.ValidatorUtils;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import com.ljnewmap.modules.sys.dto.SysDictDataDTO;
import com.ljnewmap.modules.sys.service.SysDictDataService;
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
 * 字典数据
 *
 */
@RestController
@RequestMapping("sys/dict/data")
@Tag(name = "字典数据")
@RequiredArgsConstructor
public class SysDictDataController {
    private final SysDictDataService sysDictDataService;

    @GetMapping("page")
    @Operation(summary = "字典数据")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "dictTypeId", description = "字典类型", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "dictLabel", description = "字典标签", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "dictValue", description = "字典值", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @RequiresPermissions("sys:dict:page")
    public RT<PageData<SysDictDataDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        //字典类型
        PageData<SysDictDataDTO> page = sysDictDataService.page(params);

        return new RT<PageData<SysDictDataDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dict:info")
    public RT<SysDictDataDTO> get(@PathVariable("id") Long id) {
        SysDictDataDTO data = sysDictDataService.get(id);

        return new RT<SysDictDataDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dict:save")
    public RT save(@RequestBody SysDictDataDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictDataService.save(dto);

        return new RT();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dict:update")
    public RT update(@RequestBody SysDictDataDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictDataService.update(dto);

        return new RT();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dict:delete")
    public RT delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysDictDataService.delete(ids);

        return new RT();
    }

}