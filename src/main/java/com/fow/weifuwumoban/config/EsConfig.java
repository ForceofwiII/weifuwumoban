package com.fow.weifuwumoban.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {


    @Bean
    public ElasticsearchClient createClient() {
        // 将 localhost 替换为虚拟机或远程服务器的 IP 地址
        RestClient restClient = RestClient.builder(
                new HttpHost("192.168.101.132", 9200)).build();

        // 使用 JacksonJsonpMapper 作为序列化器，构建 Transport 层
        RestClientTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // 创建 Elasticsearch 客户端
        return new ElasticsearchClient(transport);
    }

}

