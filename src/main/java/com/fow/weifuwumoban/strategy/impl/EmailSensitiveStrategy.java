package com.fow.weifuwumoban.strategy.impl;

import com.fow.weifuwumoban.strategy.SensitiveStrategy;
import lombok.Data;
import org.springframework.stereotype.Component;

// 邮箱脱敏策略
@Data
@Component(value = "email")
public class EmailSensitiveStrategy implements SensitiveStrategy {
    @Override
    public String desensitize(String value) {
        if (value == null || !value.contains("@")) {
            return value; // 数据不合法，返回原值
        }
        String[] parts = value.split("@");
        String localPart = parts[0];
        if (localPart.length() <= 2) {
            return localPart + "@****";
        }
        return localPart.charAt(0) + "****" + localPart.charAt(localPart.length() - 1) + "@" + parts[1];
    }
}