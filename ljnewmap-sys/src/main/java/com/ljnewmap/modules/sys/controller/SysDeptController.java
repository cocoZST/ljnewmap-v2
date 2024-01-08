package com.ljnewmap.modules.sys.controller;

import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.common.validator.AssertUtils;
import com.ljnewmap.common.validator.ValidatorUtils;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import com.ljnewmap.modules.sys.dto.SysDeptReqDTO;
import com.ljnewmap.modules.sys.dto.SysDeptResDTO;
import com.ljnewmap.modules.sys.dto.SysDeptTreeDTO;
import com.ljnewmap.modules.sys.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 *
 */
@RestController
@RequestMapping("/sys/dept")
@Tag(name = "部门管理")
@RequiredArgsConstructor
public class SysDeptController {
    private final SysDeptService sysDeptService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermissions("sys:dept:list")
    public RT<List<SysDeptTreeDTO>> list() {
        List<SysDeptTreeDTO> list = sysDeptService.list(new HashMap<>(1));

        return new RT<List<SysDeptTreeDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dept:info")
    public RT<SysDeptResDTO> get(@PathVariable("id") Long id) {
        SysDeptResDTO data = sysDeptService.get(id);

        return new RT<SysDeptResDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dept:save")
    public RT save(@RequestBody SysDeptReqDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysDeptService.save(dto);

        return new RT();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dept:update")
    public RT update(@RequestBody SysDeptReqDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDeptService.update(dto);

        return new RT();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dept:delete")
    public RT delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");

        sysDeptService.delete(id);

        return new RT();
    }

}