package com.fow.weifuwumoban;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class WeifuwumobanApplicationTests {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ElasticsearchClient esClient;

    @Autowired
    RedissonClient redissonClient;


    @Test
    void contextLoads() {
    }


    @Test
    public void test(){



        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setEnableTransactionSupport(true); // 开启事务支持



    }

}
