package com.point.api.mapper;

import com.point.api.ui.response.TransactionResponse;
import com.point.api.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "idx", target = "transactionIdx")
    TransactionResponse from(Transaction transaction);
}
