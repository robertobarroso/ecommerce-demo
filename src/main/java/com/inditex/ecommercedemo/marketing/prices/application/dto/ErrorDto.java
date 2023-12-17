package com.inditex.ecommercedemo.marketing.prices.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorDto {
    @JsonProperty
    private String code;

    @JsonProperty
    private String message;

    @JsonProperty
    private Map<String, String> details;

    @JsonProperty
    private LocalDateTime timestamp;

    @JsonProperty
    private String cause;
}
