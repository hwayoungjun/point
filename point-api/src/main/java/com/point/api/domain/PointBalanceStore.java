package com.point.api.domain;

import com.point.api.ui.response.PointResponse;

public interface PointBalanceStore {
    PointResponse getBalance(long userIdx);
}
