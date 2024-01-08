package com.ljnewmap.modules.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录表单
 *
 */
@Data
@Schema(description = "登录表单")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message="用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @Schema(description = "验证码")
    @NotBlank(message="验证码不能为空")
    private String captcha;

    @Schema(description = "唯一标识")
    @NotBlank(message="唯一标识不能为空")
    private String uuid;

}