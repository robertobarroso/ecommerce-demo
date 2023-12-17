package com.inditex.ecommercedemo.shared.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SystemErrorCode {
    SE0001("SE0001", "Unexpected error"),
    SE0002("SE0002", "Database error");

    @Getter
    private String code;

    @Getter
    private String description;

}
