package com.inditex.ecommercedemo.shared.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BusinessErrorCode {
    BE0001("BE0001", "Request parameters are invalid"),
    BE0002("BE0002", "Request body is invalid"),
    BE0003("BE0003", "Path variable is missing");

    @Getter
    private String code;

    @Getter
    private String message;

}
