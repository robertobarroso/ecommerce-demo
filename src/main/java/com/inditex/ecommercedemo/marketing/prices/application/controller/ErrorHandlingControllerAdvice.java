package com.inditex.ecommercedemo.marketing.prices.application.controller;

import com.inditex.ecommercedemo.marketing.prices.application.dto.ErrorDto;
import com.inditex.ecommercedemo.shared.domain.exception.BusinessErrorCode;
import com.inditex.ecommercedemo.shared.domain.exception.BusinessException;
import com.inditex.ecommercedemo.shared.domain.exception.SystemErrorCode;
import com.inditex.ecommercedemo.shared.domain.exception.SystemException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errorFieldsMap = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return buildErrorResponse(HttpStatus.BAD_REQUEST, BusinessErrorCode.BE0001.getCode(), BusinessErrorCode.BE0001.getMessage(), errorFieldsMap, ex.getCause());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errorFieldsMap = new HashMap<>();
        errorFieldsMap.put("variableName", ex.getVariableName());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, BusinessErrorCode.BE0003.getCode(), BusinessErrorCode.BE0003.getMessage(), errorFieldsMap, ex.getCause());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException e) {
        Map<String, String> errorFieldsMap  = e.getConstraintViolations().stream().collect(Collectors.toMap(key -> key.getPropertyPath().toString(), value -> value.getMessage()));
        return buildErrorResponse(HttpStatus.BAD_REQUEST, BusinessErrorCode.BE0002.getCode(), BusinessErrorCode.BE0002.getMessage(), errorFieldsMap, e.getCause());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> onAnyElseException(BusinessException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getCode(), e.getMessage(), null, null);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<Object> onAnyElseException(SystemException e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getCode(), e.getMessage(), null, e.getCause());
    }

    // Format responses of any unhandled exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> onAnyElseException(Exception e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, SystemErrorCode.SE0001.getCode(), SystemErrorCode.SE0001.getMessage(), null, e.getCause());
    }

    // Format responses of any exception thrown by Spring and not managed in this ExceptionHandler class
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        Map<String, String> errorFieldsMap = new HashMap<>();
        errorFieldsMap.put("exceptionMessage", ex.getMessage());
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, SystemErrorCode.SE0001.getCode(), SystemErrorCode.SE0001.getMessage(), errorFieldsMap, ex.getCause());
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus httpStatus, String code, String description, Map<String, String> details, Throwable cause) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(code);
        errorDto.setMessage(description);
        errorDto.setDetails(details);
        errorDto.setTimestamp(LocalDateTime.now());
        if (cause != null) {
            errorDto.setCause(cause.getMessage());
        }
        return ResponseEntity.status(httpStatus).body((Object) errorDto);
    }
}