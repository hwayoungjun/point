package com.point.api.ui;

import com.point.api.common.exception.ApiResponse;
import com.point.api.mapper.TransactionMapper;
import com.point.api.application.PointService;
import com.point.api.domain.Transaction;
import com.point.api.ui.request.AccumulationRequest;
import com.point.api.ui.request.RedeemRequest;
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
