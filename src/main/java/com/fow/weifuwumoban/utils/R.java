package com.fow.weifuwumoban.utils;

import java.io.Serial;
import java.io.Serializable;

public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 411731814484355577L;

    private int code;
    private String msg;
    private T data;

    public R() {
        this.code = 200;
        this.msg = "success";
    }

    public  R(T data ){


        this.code =200;
        this.msg = "success";
        this.data = data;

    }


    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }



    public static <T> R<T> ok( T data) {
        R<T> r = new R<>(data);

        return r;
    }

    public static R ok() {
        return new R();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
