package com.point.api.event;

import com.point.api.domain.TransactionType;
import com.point.api.domain.Transaction;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransactionEvent {
    private long userIdx;
    private TransactionType type;
    private long amount;

    public TransactionEvent(final long userIdx, final Transaction transaction) {
        this.userIdx = userIdx;
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
    }
}

