package com.fow.weifuwumoban.dto;

import com.fow.weifuwumoban.annotation.ValidUserLogin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@ValidUserLogin // 自定义注解
// 用户登录或注册dto
public class UserLoginDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    private String code;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "邮箱格式不正确")
    private String email;
}
