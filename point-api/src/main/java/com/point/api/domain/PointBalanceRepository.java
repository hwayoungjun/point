package com.point.api.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PointBalanceRepository extends CrudRepository<PointBalance, String> {
    Optional<PointBalance> findByUserIdx(long userIdx);
}
