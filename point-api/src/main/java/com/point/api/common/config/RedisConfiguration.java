package com.point.api.common.config;

import com.point.api.ui.response.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    private final LettuceConnectionFactory lettuceConnectionFactory;

    @Bean("pointBalanceRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new PrefixStringRedisSerializer("balance:"));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(PointResponse.class));
        return redisTemplate;
    }

}
