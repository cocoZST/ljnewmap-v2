package com.ljnewmap.modules.sys.controller;

import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.exception.SysErrorCodeConstants;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.common.utils.ConvertUtils;
import com.ljnewmap.common.utils.ExcelUtils;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.common.validator.AssertUtils;
import com.ljnewmap.common.validator.ValidatorUtils;
import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import com.ljnewmap.modules.security.password.PasswordUtils;
import com.ljnewmap.modules.security.user.SecurityUser;
import com.ljnewmap.modules.security.user.UserDetail;
import com.ljnewmap.modules.sys.dto.PasswordDTO;
import com.ljnewmap.modules.sys.dto.SysUserDTO;
import com.ljnewmap.modules.sys.dto.SysUserPostDTO;
import com.ljnewmap.modules.sys.excel.SysUserExcel;
import com.ljnewmap.modules.sys.service.SysRoleUserService;
import com.ljnewmap.modules.sys.service.SysUserPostService;
import com.ljnewmap.modules.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 */
@RestController
@RequestMapping("/sys/user")
@Tag(name = "用户管理")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;
    private final SysRoleUserService sysRoleUserService;
    private final SysUserPostService sysUserPostService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "username", description = "用户名", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "gender", description = "性别", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "deptId", description = "部门ID", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @RequiresPermissions("sys:user:page")
    public RT<PageData<SysUserDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysUserDTO> page = sysUserService.page(params);

        return new RT<PageData<SysUserDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:user:info")
    public RT<SysUserDTO> get(@PathVariable("id") Long id) {
        SysUserDTO data = sysUserService.get(id);

        //用户角色列表
        List<Long> roleIdList = sysRoleUserService.getRoleIdList(id);
        data.setRoleIdList(roleIdList);
        //用户岗位列表
        List<SysUserPostDTO> userPostDTOList = sysUserPostService.getUserPostList(id);
        data.setUserPostList(userPostDTOList);
        return new RT<SysUserDTO>().ok(data);
    }

    @GetMapping("info")
    @Operation(summary = "登录用户信息")
    public RT<SysUserDTO> info() {
        SysUserDTO data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
        return new RT<SysUserDTO>().ok(data);
    }

    @PutMapping("password")
    @Operation(summary = "修改密码")
    @LogOperation("修改密码")
    public RT password(@RequestBody PasswordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto);

        UserDetail user = SecurityUser.getUser();

        //原密码不正确
        if (!PasswordUtils.matches(dto.getPassword(), user.getPassword())) {
            return new RT().error(SysErrorCodeConstants.PASSWORD_ERROR);
        }

        sysUserService.updatePassword(user.getId(), dto.getNewPassword());

        return new RT();
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:user:save")
    public RT save(@RequestBody SysUserDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        List<SysUserPostDTO> sysUserPostDTOS = dto.getUserPostList();
        if(sysUserPostDTOS != null && sysUserPostDTOS.size() > 0){
            for(SysUserPostDTO sysUserPostDTO:sysUserPostDTOS){
                ValidatorUtils.validateEntity(sysUserPostDTO, DefaultGroup.class);
            }
        }

        sysUserService.save(dto);

        return new RT();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:user:update")
    public RT update(@RequestBody SysUserDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        List<SysUserPostDTO> sysUserPostDTOS = dto.getUserPostList();
        if(sysUserPostDTOS != null && sysUserPostDTOS.size() > 0){
            for(SysUserPostDTO sysUserPostDTO:sysUserPostDTOS){
                ValidatorUtils.validateEntity(sysUserPostDTO, DefaultGroup.class);
            }
        }

        sysUserService.update(dto);

        return new RT();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:user:delete")
    public RT delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysUserService.delete(ids);

        return new RT();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:user:export")
    @Parameter(name = "username", description = "用户名", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysUserDTO> list = sysUserService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "用户管理", list, SysUserExcel.class);
    }
}