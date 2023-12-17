package com.inditex.ecommercedemo.shared.domain.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    @Getter
    private String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
}
