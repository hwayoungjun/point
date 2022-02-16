package com.point.transaction.event;

import com.point.transaction.domain.Transaction;
import com.point.transaction.domain.TransactionType;
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

