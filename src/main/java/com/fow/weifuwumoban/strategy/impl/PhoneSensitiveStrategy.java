package com.fow.weifuwumoban.strategy.impl;

import com.fow.weifuwumoban.strategy.SensitiveStrategy;
import lombok.Data;
import org.springframework.stereotype.Component;

// 手机号脱敏策略
@Data
@Component(value = "phone")
public class PhoneSensitiveStrategy implements SensitiveStrategy {
    @Override
    public String desensitize(String value) {
        if (value == null || value.length() < 10) {
            return value; // 数据不合法，返回原值
        }
        return value.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}