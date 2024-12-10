package com.fow.weifuwumoban.enums;

public enum PaymentMethodEnum {
    CREDIT_CARD(3001, "信用卡"),
    PAYPAL(3002, "PayPal"),
    WECHAT(3003, "微信支付"),
    ALIPAY(3004, "支付宝"),
    BANK_TRANSFER(3005, "银行转账");

    private final int code;
    private final String msg;

    PaymentMethodEnum(int code, String msg) {
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
