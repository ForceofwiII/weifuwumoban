package com.fow.weifuwumoban;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.fow.weifuwumoban.mapper")
@EnableFeignClients
public class WeifuwumobanApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeifuwumobanApplication.class, args);
    }

}
