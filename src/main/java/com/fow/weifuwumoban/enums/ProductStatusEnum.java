package com.fow.weifuwumoban.enums;

public enum ProductStatusEnum {
    AVAILABLE(4001, "上架"),
    OUT_OF_STOCK(4002, "缺货"),
    DISCONTINUED(4003, "已下架");

    private final int code;
    private final String msg;

    ProductStatusEnum(int code, String msg) {
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
