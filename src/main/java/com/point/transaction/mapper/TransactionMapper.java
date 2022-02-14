package com.point.transaction.mapper;

import com.point.transaction.domain.Transaction;
import com.point.transaction.ui.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "idx", target = "transactionIdx")
    TransactionResponse from(Transaction transaction);
}
