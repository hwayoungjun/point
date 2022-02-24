package com.point.api.infra;

import com.point.api.domain.Point;
import com.point.api.domain.PointBalance;
import com.point.api.domain.PointBalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import({EmbeddedRedis.class, TestRedisConfiguration.class})
@DataRedisTest
class DefaultPointBalanceStoreTest {

    @Autowired
    PointBalanceRepository pointBalanceRepository;

    @Test
    void getBalance() {
        Point point = Point.builder()
                .userIdx(1L)
                .amount(1000).build();
        pointBalanceRepository.save(PointBalance.from(point));

        PointBalance pointBalance = pointBalanceRepository.findByUserIdx(point.getUserIdx())
                .orElse(null);

        assertEquals(point.getUserIdx(), pointBalance.getUserIdx());
        assertEquals(point.getAmount(), pointBalance.getAmount());
    }
}