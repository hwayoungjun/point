package com.point.transaction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "point_transaction")
@Getter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private TransactionType type;

    private long amount;

    private LocalDateTime expireDate;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transaction_idx")
    private Set<TransactionDetail> transactionDetails = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createDate;

    public static Transaction accumulate(final long amount) {
        Transaction transaction = Transaction.builder()
                .type(TransactionType.ACCUMULATION)
                .amount(amount)
                .expireDate(LocalDate.now().plusDays(30).atTime(0, 0, 0, 0).minusNanos(1))
                .build();
        return transaction.mapping(Collections.singleton(TransactionDetail.accumulate(transaction)));
    }

    private Transaction mapping(final Set<TransactionDetail> transactionDetails) {
        this.transactionDetails.addAll(transactionDetails);
        return this;
    }

    @Builder
    private Transaction(final TransactionType type, final long amount, final LocalDateTime expireDate) {
        this.type = type;
        this.amount = amount;
        this.expireDate = expireDate;
    }
}
