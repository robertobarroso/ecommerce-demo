package com.inditex.ecommercedemo.marketing.prices.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PriceCriteriaDto {
    @JsonProperty
    @NotNull
    private Long productId;

    @JsonProperty
    @NotNull
    private Long brandId;

    @JsonProperty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull
    private LocalDateTime date;
}
