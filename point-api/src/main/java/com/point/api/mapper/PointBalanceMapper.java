package com.point.api.mapper;

import com.point.api.domain.PointBalance;
import com.point.api.ui.response.PointResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointBalanceMapper {
    PointBalanceMapper INSTANCE = Mappers.getMapper(PointBalanceMapper.class);

    PointResponse from(PointBalance pointBalance);
}
