package com.point.transaction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private long amount;

    private LocalDateTime expireDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "transaction_idx")
    private Set<TransactionDetail> transactionDetails = new HashSet<>();

    @OneToMany(mappedBy = "transaction")
    private Set<TransactionDetail> originTransactionDetails = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createDate;

    public boolean isAccumulation() {
        return TransactionType.ACCUMULATION.equals(type);
    }

    public boolean hasBalance() {
        return getBalance() > 0;
    }

    public long getBalance() {
        long rdmAmount = originTransactionDetails.stream()
                .filter(TransactionDetail::isRedemption)
                .mapToLong(TransactionDetail::getAmount)
                .sum();
        return amount - rdmAmount;
    }

    public TransactionDetail deduct(final long amount) {
        long balance = getBalance();
        return TransactionDetail.deduct(balance >= amount ? amount : balance, this);
    }

    public Transaction reflectDeduction(final long deductionAmount) {
        this.amount = deductionAmount;
        return this;
    }

    public static Transaction accumulate(final long amount) {
        Transaction transaction = Transaction.builder()
                .type(TransactionType.ACCUMULATION)
                .amount(amount)
                .expireDate(LocalDate.now().plusDays(30).atTime(0, 0, 0, 0).minusNanos(1))
                .build();
        return transaction.mapping(TransactionDetail.accumulate(transaction));
    }

    public static Transaction redeem() {
        return Transaction.builder()
                .type(TransactionType.REDEMPTION)
                .amount(0l)
                .build();
    }

    public Transaction mapping(final TransactionDetail transactionDetail) {
        this.transactionDetails.add(transactionDetail);
        return this;
    }

    @Builder
    private Transaction(final TransactionType type, final long amount, final LocalDateTime expireDate) {
        this.type = type;
        this.amount = amount;
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idx=" + idx +
                ", type=" + type +
                ", amount=" + amount +
                ", expireDate=" + expireDate +
                ", createDate=" + createDate +
                '}';
    }
}
