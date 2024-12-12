package com.fow.weifuwumoban.annotation;

import com.fow.weifuwumoban.jiaoyan.UserLoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserLoginValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserLogin {

    String message() default "用户登录信息不符合要求";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
