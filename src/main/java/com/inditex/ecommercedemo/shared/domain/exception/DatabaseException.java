package com.inditex.ecommercedemo.shared.domain.exception;

import java.text.MessageFormat;

public class DatabaseException extends SystemException {

    public DatabaseException(String service, Throwable cause) {
        super(SystemErrorCode.SE0002.getCode(),
                MessageFormat.format(SystemErrorCode.SE0002.getMessage(), service),
                cause);

    }
}
