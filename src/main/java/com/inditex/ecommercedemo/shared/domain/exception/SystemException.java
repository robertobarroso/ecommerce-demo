package com.inditex.ecommercedemo.shared.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SystemException extends RuntimeException {

    private SystemErrorCode code;

    private String description;
}
