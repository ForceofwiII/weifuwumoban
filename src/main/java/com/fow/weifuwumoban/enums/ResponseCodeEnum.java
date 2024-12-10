package com.fow.weifuwumoban.enums;

public enum ResponseCodeEnum {
    SUCCESS(0, "操作成功"),
    ERROR(5000, "服务器内部错误"),
    VALIDATION_FAILED(4001, "参数校验失败"),
    UNAUTHORIZED(4003, "未授权"),
    FORBIDDEN(4004, "无权限"),
    NOT_FOUND(4005, "资源不存在");

    private final int code;
    private final String msg;

    ResponseCodeEnum(int code, String msg) {
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
