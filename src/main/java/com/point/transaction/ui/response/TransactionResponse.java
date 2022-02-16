package com.point.transaction.ui.response;

import com.point.transaction.domain.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse {
    private long transactionIdx;
    private TransactionType type;
    private long amount;
    private LocalDateTime expireDate;


}
