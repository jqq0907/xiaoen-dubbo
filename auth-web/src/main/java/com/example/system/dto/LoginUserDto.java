package com.example.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangqiangqiang
 * @description: 登录dto
 * @date 2021/11/20 4:23 下午
 */
@Data
@Schema
public class LoginUserDto {
    /**
     * 用户名
     */
    @NotBlank
    @Schema(name = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @Schema(name = "密码", required = true)
    private String password;

    /**
     * 验证码
     */
    @Schema(name = "验证码", required = true)
    private String verCode;

    /**
     * 验证码key
     */
    @Schema(name = "验证码key", required = true)
    private String verKey;
}
