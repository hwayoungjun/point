package com.point.transaction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "point_transaction_detail")
@Getter
@NoArgsConstructor
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private TransactionType type;

    private long amount;

    @OneToOne
    @JoinColumn(name = "original_transaction_id")
    private Transaction transaction;

    @CreationTimestamp
    private LocalDateTime createDate;

    public static TransactionDetail accumulate(final Transaction transaction) {
        return TransactionDetail.builder()
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .transaction(transaction)
                .build();
    }

    @Builder
    private TransactionDetail(final TransactionType type, final long amount, final Transaction transaction) {
        this.type = type;
        this.amount = amount;
        this.transaction = transaction;
    }
}
