package com.point.api.ui.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor
public class PointResponse {
    private long amount;
    private LocalDateTime updateDate;

    @Builder
    private PointResponse(long amount, LocalDateTime updateDate) {
        this.amount = amount;
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointResponse that = (PointResponse) o;
        return amount == that.amount && updateDate.equals(that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, updateDate);
    }
}
