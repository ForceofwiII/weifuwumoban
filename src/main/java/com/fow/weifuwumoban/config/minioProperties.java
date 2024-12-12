package com.fow.weifuwumoban.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio")
public class minioProperties {




    private String  endpoint;

    private String  accessKey;

    private String  secretKey;


}
