package com.fow.weifuwumoban.annotation;

import com.fow.weifuwumoban.enums.SensitiveType;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sensitive {
    String type();
}
