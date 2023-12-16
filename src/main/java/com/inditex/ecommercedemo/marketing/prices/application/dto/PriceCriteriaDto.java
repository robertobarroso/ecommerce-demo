package com.inditex.ecommercedemo.marketing.prices.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PriceCriteriaDto {
    @NotNull
    private Long productId;
    @NotNull
    private Long brandId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull
    private LocalDateTime date;
}
