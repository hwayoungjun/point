package com.point.transaction.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    NO_BALANCE(1, "포인트가 부족합니다.");

    int code;
    String message;
    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
