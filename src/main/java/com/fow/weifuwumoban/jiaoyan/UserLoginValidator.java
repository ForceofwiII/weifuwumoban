package com.fow.weifuwumoban.jiaoyan;

import com.fow.weifuwumoban.annotation.ValidUserLogin;
import com.fow.weifuwumoban.dto.UserLoginDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserLoginValidator implements ConstraintValidator<ValidUserLogin, UserLoginDto> {

    @Override
    public void initialize(ValidUserLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserLoginDto value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null 的情况交给 @NotNull 处理
        }

        boolean isUserNamePresent = value.getUserName() != null && !value.getUserName().trim().isEmpty();
        boolean isPhoneOrEmailPresent = 
            (value.getPhone() != null && !value.getPhone().trim().isEmpty()) ||
            (value.getEmail() != null && !value.getEmail().trim().isEmpty());
        boolean isPasswordPresent = value.getPassword() != null && !value.getPassword().trim().isEmpty();
        boolean isCodePresent = value.getCode() != null && !value.getCode().trim().isEmpty();

        // 校验逻辑
        if (isUserNamePresent && !isPasswordPresent) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("如果用户名不为空，则密码不能为空")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        if (isPhoneOrEmailPresent && !isCodePresent) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("如果手机号或邮箱不为空，则验证码不能为空")
                    .addPropertyNode("code")
                    .addConstraintViolation();
            return false;
        }

        if (!isUserNamePresent && !isPhoneOrEmailPresent) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("用户名、手机号或邮箱至少填写一个")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
