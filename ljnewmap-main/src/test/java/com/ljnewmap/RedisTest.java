package com.ljnewmap;

import com.ljnewmap.common.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RequiredArgsConstructor
public class RedisTest {
    private final RedisUtils redisUtils;

    @Test
    public void contextLoads() {
    }

}