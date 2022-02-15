package com.point.transaction.ui;

import com.point.transaction.application.PointService;
import com.point.transaction.common.exception.ApiResponse;
import com.point.transaction.domain.Transaction;
import com.point.transaction.mapper.TransactionMapper;
import com.point.transaction.ui.request.AccumulationRequest;
import com.point.transaction.ui.request.RedeemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PointRestController {

    private final PointService pointService;

    @PostMapping("/accumulate")
    public ResponseEntity<ApiResponse> accumulate(@RequestBody AccumulationRequest request) {
        Transaction accumulation = pointService.accumulate(request);
        return ResponseEntity.ok(ApiResponse.ok(TransactionMapper.INSTANCE.from(accumulation)));
    }

    @PostMapping("/redeem")
    public ResponseEntity<ApiResponse> redeem(@RequestBody RedeemRequest request) {
        Transaction redemption = pointService.redeem(request);
        return ResponseEntity.ok(ApiResponse.ok(TransactionMapper.INSTANCE.from(redemption)));
    }
}
