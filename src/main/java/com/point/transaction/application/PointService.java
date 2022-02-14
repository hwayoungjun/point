package com.point.transaction.application;

import com.point.transaction.domain.Point;
import com.point.transaction.domain.PointRepository;
import com.point.transaction.domain.Transaction;
import com.point.transaction.ui.request.AccumulationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    public Transaction accumulate(AccumulationRequest request) {
        long userIdx = request.getUserIdx();
        Point point = pointRepository.findByUserIdx(userIdx).orElse(Point.of(userIdx));

        Transaction accumulation = point.accumulate(request.getAmount());
        pointRepository.save(point);
        return accumulation;
    }
}
