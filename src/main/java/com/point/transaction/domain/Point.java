package com.point.transaction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "point_idx")
    private Set<Transaction> transactions = new HashSet<>();

    public static Point of(final long userIdx) {
        return Point.builder()
                .userIdx(userIdx)
                .amount(0l)
                .build();
    }

    public Transaction accumulate(final long amount) {
        Transaction accumulation = Transaction.accumulate(amount);
        this.transactions.add(accumulation);
        return accumulation;
    }

    @Builder
    private Point(final Long userIdx, final long amount) {
        this.userIdx = userIdx;
        this.amount = amount;
    }
}
