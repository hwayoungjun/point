package com.point.transaction.application;

import com.point.transaction.common.exception.ExceptionCode;
import com.point.transaction.domain.Point;
import com.point.transaction.domain.PointRepository;
import com.point.transaction.domain.Transaction;
import com.point.transaction.common.exception.BusinessException;
import com.point.transaction.ui.request.AccumulationRequest;
import com.point.transaction.ui.request.RedeemRequest;
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

    public Transaction redeem(RedeemRequest request) {
        long userIdx = request.getUserIdx();
        long redemptionAmount = request.getAmount();
        Point point = pointRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new BusinessException(ExceptionCode.NO_BALANCE));

        if(!point.isRedeemable(redemptionAmount)) {
            throw new BusinessException(ExceptionCode.NO_BALANCE);
        }

        Transaction redemption = point.redeem(redemptionAmount);
        pointRepository.save(point);
        return redemption;
    }
}
