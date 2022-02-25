package com.point.api.infra;

import com.point.api.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultPointBalanceStore implements PointBalanceStore {

    private final PointBalanceRepository pointBalanceRepository;
    private final PointRepository pointRepository;

    @Override
    public PointBalance getBalance(long userIdx) {
        return pointBalanceRepository.findByUserIdx(userIdx).orElseGet(() -> {
            Point point = pointRepository.findByUserIdx(userIdx).orElseGet(() -> {
                //todo publish init-balance event
                return Point.of(userIdx);
            });
            return PointBalance.from(point);
        });
    }
}
