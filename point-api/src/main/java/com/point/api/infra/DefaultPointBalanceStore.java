package com.point.api.infra;

import com.point.api.domain.PointBalanceStore;
import com.point.api.ui.response.PointResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@RequiredArgsConstructor
public class DefaultPointBalanceStore implements PointBalanceStore {

    @Qualifier("pointBalanceRedisTemplate")
    private final RedisTemplate redisTemplate;

    @Override
    public PointResponse getBalance(long userIdx) {
        return (PointResponse) redisTemplate.opsForValue().get(userIdx);
    }
}
