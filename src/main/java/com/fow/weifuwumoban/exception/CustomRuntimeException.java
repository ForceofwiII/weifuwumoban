package com.fow.weifuwumoban.exception;

public class CustomRuntimeException extends RuntimeException {
    private final int code; // 异常代码
    private final String msg; // 异常消息

    // 构造方法：接收 code 和 msg
    public CustomRuntimeException(int code, String msg) {
        super(msg);  // 调用父类构造方法，将 msg 作为异常消息传递
        this.code = code;
        this.msg = msg;
    }

    // 获取异常代码
    public int getCode() {
        return code;
    }

    // 获取异常消息
    public String getMsg() {
        return msg;
    }
}
