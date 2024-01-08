package com.ljnewmap.modules.sys.controller;

import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.exception.SysErrorCodeConstants;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.common.validator.AssertUtils;
import com.ljnewmap.common.validator.ValidatorUtils;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.modules.security.service.ShiroService;
import com.ljnewmap.modules.security.user.SecurityUser;
import com.ljnewmap.modules.security.user.UserDetail;
import com.ljnewmap.modules.sys.dto.SysMenuResDTO;
import com.ljnewmap.modules.sys.dto.SysMenuTreeDTO;
import com.ljnewmap.modules.sys.dto.SysMenuReqDTO;
import com.ljnewmap.modules.sys.enums.MenuTypeEnum;
import com.ljnewmap.modules.sys.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 */
@RestController
@RequestMapping("/sys/menu")
@Tag(name = "菜单管理")
@RequiredArgsConstructor
public class SysMenuController {
    private final SysMenuService sysMenuService;
    private final ShiroService shiroService;

    @GetMapping("nav")
    @Operation(summary = "导航")
    public RT<List<SysMenuTreeDTO>> nav() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuTreeDTO> list = sysMenuService.getUserMenuList(user, MenuTypeEnum.MENU.value());

        return new RT<List<SysMenuTreeDTO>>().ok(list);
    }

    @GetMapping("permissions")
    @Operation(summary = "权限标识")
    public RT<Set<String>> permissions() {
        UserDetail user = SecurityUser.getUser();
        Set<String> set = shiroService.getUserPermissions(user);

        return new RT<Set<String>>().ok(set);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @Parameter(name = "type", description = "菜单类型 0：菜单 1：按钮  null：全部", in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    @RequiresPermissions("sys:menu:list")
    public RT<List<SysMenuTreeDTO>> list(Integer type) {
        List<SysMenuTreeDTO> list = sysMenuService.getAllMenuList(type);

        return new RT<List<SysMenuTreeDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:menu:info")
    public RT<SysMenuResDTO> get(@PathVariable("id") Long id) {
        SysMenuResDTO data = sysMenuService.get(id);

        return new RT<SysMenuResDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:menu:save")
    public RT save(@RequestBody SysMenuReqDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.save(dto);

        return new RT();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:menu:update")
    public RT update(@RequestBody SysMenuReqDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.update(dto);

        return new RT();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:menu:delete")
    public RT delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");

        //判断是否有子菜单或按钮
        List<SysMenuTreeDTO> list = sysMenuService.getListPid(id);
        if (list.size() > 0) {
            return new RT().error(SysErrorCodeConstants.MENU_SUB_EXIST);
        }

        sysMenuService.delete(id);

        return new RT();
    }

    @GetMapping("select")
    @Operation(summary = "角色菜单权限")
    @RequiresPermissions("sys:menu:select")
    public RT<List<SysMenuTreeDTO>> select() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuTreeDTO> list = sysMenuService.getUserMenuList(user, null);

        return new RT<List<SysMenuTreeDTO>>().ok(list);
    }
}