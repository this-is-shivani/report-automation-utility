package com.ecowishlist.ReportAutomationTool.CustomerReport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void redisTest() {
        redisTemplate.opsForValue().set("spring-boot-redis-test","pass");
        assertEquals("pass",redisTemplate.opsForValue().get("spring-boot-redis-test"));
    }
}
