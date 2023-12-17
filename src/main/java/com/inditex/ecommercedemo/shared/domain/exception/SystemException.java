package com.inditex.ecommercedemo.shared.domain.exception;

import lombok.Getter;

public class SystemException extends RuntimeException {

    @Getter
    private String code;

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
