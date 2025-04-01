package com.fow.weifuwumoban;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.alibaba.fastjson2.JSON;
import com.fow.weifuwumoban.entity.User;
import com.fow.weifuwumoban.exception.CustomRuntimeException;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
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


    @Autowired
    KieSession kieSession;


    @Test
    void contextLoads() {
    }


    @Test
    public void test(){





//        kieSession.insert();
//        kieSession.setGlobal();
//        kieSession.fireAllRules();
//        kieSession.dispose();



        throw new CustomRuntimeException(400,"错误");



    }

}
