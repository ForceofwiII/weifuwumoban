package com.fow.weifuwumoban.enums;

public enum SensitiveType {
    PHONE("phone"),  // 手机号脱敏
    EMAIL("email") ;  // 邮箱脱敏


    private final String msg;

    SensitiveType( String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

}
