package com.fow.weifuwumoban.enums;

public enum UserRoleEnum {
    CUSTOMER(2001, "普通用户"),
    ADMIN(2002, "管理员"),
    SELLER(2003, "卖家"),
    BAIYIN(2004, "白银会员"),
    HUANGJIN(2005, "黄金会员"),
    ZUANSHI(2006, "钻石会员");

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

    public static UserRoleEnum getRoleByCode(int code) {
        for (UserRoleEnum value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public static UserRoleEnum getRoleByMsg(String msg) {
        for (UserRoleEnum value : values()) {
            if (value.getMsg().equals(msg)) {
                return value;
            }
        }
        return null;
    }


}
