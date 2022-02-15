package com.point.transaction.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public static ApiResponse ok() {
        return ApiResponse.builder()
                .code(0)
                .message("success")
                .build();
    }

    public static <T> ApiResponse ok(T data) {
        return ApiResponse.builder()
                .code(0)
                .message("success")
                .data(data)
                .build();
    }

    public static ApiResponse from(BusinessException exception) {
        return ApiResponse.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
    }

    @Builder
    private ApiResponse(final int code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
