package com.point.api.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;

public class GenericRedisTemplate extends RedisTemplate {

    private ObjectMapper objectMapper;

    public GenericRedisTemplate(ObjectMapper objectMapper) {
        super();
        this.objectMapper = objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public<T> T opsForValueAndGet(Object key, Class<T> dataType) throws JsonProcessingException {
        String value = (String) this.opsForValue().get(key);
        return objectMapper.readValue(value, dataType);
    }
}
