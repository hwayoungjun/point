package com.point.transaction.ui;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
