package com.ljnewmap.modules.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 修改密码
 *
 */
@Data
@Schema(description = "修改密码")
public class PasswordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "原密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @Schema(description = "新密码")
    @NotBlank(message="密码不能为空")
    private String newPassword;

}