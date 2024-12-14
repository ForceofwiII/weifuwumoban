package com.fow.weifuwumoban.strategy;

import com.fow.weifuwumoban.enums.SensitiveType;
import com.fow.weifuwumoban.strategy.impl.EmailSensitiveStrategy;
import com.fow.weifuwumoban.strategy.impl.PhoneSensitiveStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class SensitiveContext {

    @Autowired
    Map<String, SensitiveStrategy> strategyMap;

    public  String desensitize(String type, String value) {


        SensitiveStrategy sensitiveStrategy = strategyMap.get(type);
        if (sensitiveStrategy != null) {
            return sensitiveStrategy.desensitize(value);
        }


        return value;




    }
}
