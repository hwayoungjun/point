package com.point.api.domain;

import com.point.api.event.TransactionEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class Point extends AbstractAggregateRoot<Point> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private long userIdx;

    private long amount;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "point_idx")
    private List<Transaction> transactions = new ArrayList<>();

    public boolean isRedeemable(final long amount) {
        return this.amount >= amount;
    }

    public Transaction accumulate(final long amount) {
        Transaction accumulation = Transaction.accumulate(amount);
        this.transactions.add(accumulation);
        registerEvent(new TransactionEvent(userIdx, accumulation));
        return accumulation;
    }

    public Transaction redeem(final long amount) {
        List<Transaction> accumulations = transactions.stream()
                .filter(Transaction::isAccumulation)
                .filter(Transaction::hasBalance)
                .sorted(Comparator.comparing(Transaction::getExpireDate))
                .collect(Collectors.toList());

        AtomicLong leftDeductionAmount = new AtomicLong(amount);
        Transaction redemption = Transaction.redeem();
        accumulations.forEach(accumulation -> {
            if(leftDeductionAmount.get() <= 0) {
                return;
            }
            TransactionDetail deduction = accumulation.deduct(leftDeductionAmount.get());
            leftDeductionAmount.addAndGet(deduction.getSignedAmount());
            redemption.mapping(deduction);
        });

        this.transactions.add(redemption.reflectDeduction(amount));
        registerEvent(new TransactionEvent(userIdx, redemption));
        return redemption;
    }

    public static Point of(final long userIdx) {
        return Point.builder()
                .userIdx(userIdx)
                .amount(0l)
                .build();
    }

    @Builder
    private Point(final Long userIdx, final long amount) {
        this.userIdx = userIdx;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Point{" +
                "idx=" + idx +
                ", userIdx=" + userIdx +
                ", amount=" + amount +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
