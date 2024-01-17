package com.ljnewmap.modules.security.controller;

import com.ljnewmap.common.exception.SysErrorCodeConstants;
import com.ljnewmap.modules.log.entity.SysLogLoginEntity;
import com.ljnewmap.modules.security.service.CaptchaService;
import com.ljnewmap.modules.security.service.SysUserTokenService;
import com.ljnewmap.common.exception.RRException;
import com.ljnewmap.common.utils.IpUtils;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.common.validator.AssertUtils;
import com.ljnewmap.common.validator.ValidatorUtils;
import com.ljnewmap.modules.log.enums.LoginOperationEnum;
import com.ljnewmap.modules.log.enums.LoginStatusEnum;
import com.ljnewmap.modules.log.service.SysLogLoginService;
import com.ljnewmap.modules.security.dto.LoginDTO;
import com.ljnewmap.modules.security.password.PasswordUtils;
import com.ljnewmap.modules.security.user.SecurityUser;
import com.ljnewmap.modules.security.user.UserDetail;
import com.ljnewmap.modules.sys.dto.SysUserDTO;
import com.ljnewmap.modules.sys.enums.UserStatusEnum;
import com.ljnewmap.modules.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 登录
 *
 */
@RestController
@Tag(name = "登录管理")
@RequiredArgsConstructor
public class LoginController {
    private final SysUserService sysUserService;
    private final SysUserTokenService sysUserTokenService;
    private final CaptchaService captchaService;
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("captcha")
    @Operation(summary = "验证码")
    @ApiResponse(content = @Content(mediaType = "application/octet-stream"))
    @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {

        //uuid不能为空
        AssertUtils.isBlank(uuid, SysErrorCodeConstants.CAPTCHA_NOT_NULL);

        //生成验证码
        captchaService.create(response, uuid);
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public RT login(HttpServletRequest request, @RequestBody LoginDTO login) {
        //效验数据
        ValidatorUtils.validateEntity(login);

        //验证码是否正确
//        boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
        boolean flag = true;
        if (!flag) {
            return new RT().error(SysErrorCodeConstants.CAPTCHA_ERROR);
        }

        //用户信息
        SysUserDTO user = sysUserService.getByUsername(login.getUsername());

        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

        //用户不存在
        if (user == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            sysLogLoginService.save(log);

            throw new RRException(SysErrorCodeConstants.ACCOUNT_PASSWORD_ERROR);
        }

        //密码错误
        if (!PasswordUtils.matches(login.getPassword(), user.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RRException(SysErrorCodeConstants.ACCOUNT_PASSWORD_ERROR);
        }

        //账号停用
        if (UserStatusEnum.DISABLE.value().equals(user.getStatus())) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RRException(SysErrorCodeConstants.ACCOUNT_DISABLE);
        }

        //登录成功
        log.setOperation(LoginOperationEnum.LOGIN.value());
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        sysLogLoginService.save(log);

        return sysUserTokenService.createToken(user.getId());
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public RT logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //退出
        sysUserTokenService.logout(user.getId());

        //用户信息
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return new RT();
    }

}