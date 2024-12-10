package com.fow.weifuwumoban.enums;

public enum UserRoleEnum {
    CUSTOMER(2001, "普通用户"),
    ADMIN(2002, "管理员"),
    SELLER(2003, "卖家");

    private final int code;
    private final String msg;

    UserRoleEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
