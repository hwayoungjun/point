package com.point.api.domain;

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

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private long amount;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "original_transaction_id")
    private Transaction transaction;

    @CreationTimestamp
    private LocalDateTime createDate;

    public boolean isRedemption() {
        return TransactionType.REDEMPTION.equals(type);
    }

    public long getSignedAmount() {
        return TransactionType.ACCUMULATION.equals(type) ? amount : amount * -1;
    }

    public static TransactionDetail accumulate(final Transaction transaction) {
        return TransactionDetail.builder()
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .transaction(transaction)
                .build();
    }

    public static TransactionDetail deduct(final long amount, final Transaction originTransaction) {
        return TransactionDetail.builder()
                .type(TransactionType.REDEMPTION)
                .amount(amount)
                .transaction(originTransaction)
                .build();
    }

    @Builder
    private TransactionDetail(final TransactionType type, final long amount, final Transaction transaction) {
        this.type = type;
        this.amount = amount;
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "idx=" + idx +
                ", type=" + type +
                ", amount=" + amount +
                ", createDate=" + createDate +
                '}';
    }
}
