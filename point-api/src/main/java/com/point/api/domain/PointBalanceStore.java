package com.point.api.domain;

public interface PointBalanceStore {
    PointBalance getBalance(long userIdx);
}
