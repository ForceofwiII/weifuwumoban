package com.fow.weifuwumoban;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.alibaba.fastjson2.JSON;
import com.fow.weifuwumoban.entity.User;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class WeifuwumobanApplicationTests {


    private static final Logger log = LoggerFactory.getLogger(WeifuwumobanApplicationTests.class);
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


        User user = new User();

        user.setUserName("test");
        user.setPassword("123");
        user.setUserRole("123");


        String json = JSON.toJSONString(user);
        System.out.println(json);




    }

}
