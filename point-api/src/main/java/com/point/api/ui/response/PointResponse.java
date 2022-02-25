package com.point.api.ui.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
public class PointResponse {
    private long amount;
    private LocalDateTime updateDate;

    @Builder
    private PointResponse(long amount, LocalDateTime updateDate) {
        this.amount = amount;
        this.updateDate = updateDate;
    }
}
