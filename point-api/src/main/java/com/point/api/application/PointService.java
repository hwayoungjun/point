package com.point.api.application;

import com.point.api.common.exception.BusinessException;
import com.point.api.common.exception.ExceptionCode;
import com.point.api.domain.*;
import com.point.api.ui.request.AccumulationRequest;
import com.point.api.ui.request.RedeemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final PointBalanceStore pointBalanceStore;

    public PointBalance getBalance(long userIdx) {
        return pointBalanceStore.getBalance(userIdx);
    }

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
