package com.point.api.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.point.api.common.config.GenericRedisTemplate;
import com.point.api.ui.response.PointResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import({EmbeddedRedis.class, TestRedisConfiguration.class})
@DataRedisTest
class DefaultPointBalanceStoreTest {

    @Qualifier("testPointBalanceRedisTemplate")
    @Autowired
    GenericRedisTemplate genericRedisTemplate;

    ObjectMapper objectMapper;

    @BeforeAll
    void init() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.registerModules(new JavaTimeModule());
    }

    @Test
    void getBalance() throws JsonProcessingException {
        PointResponse pointResponse = PointResponse.builder()
                .amount(1000)
                .updateDate(LocalDateTime.now())
                .build();

        genericRedisTemplate.opsForValue().set("test-user-1", objectMapper.writeValueAsString(pointResponse), Duration.ofHours(1l));
        assertEquals(genericRedisTemplate.opsForValueAndGet("test-user-1", PointResponse.class), pointResponse);
    }
}