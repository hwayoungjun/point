package com.point.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@RedisHash("user")
@Getter
@ToString
public class PointBalance {

    @Id
    private String id;

    @Indexed
    private long userIdx;

    private long amount;

    private LocalDateTime updateDate;

    public static PointBalance from(final Point point) {
        return PointBalance.builder()
                .userIdx(point.getUserIdx())
                .amount(point.getAmount())
                .updateDate(point.getUpdateDate())
                .build();
    }

    @Builder
    private PointBalance(final long userIdx, final long amount, final LocalDateTime updateDate) {
        this.userIdx = userIdx;
        this.amount = amount;
        this.updateDate = updateDate;
    }
}
