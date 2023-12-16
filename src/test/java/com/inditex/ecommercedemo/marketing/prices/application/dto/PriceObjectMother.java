package com.inditex.ecommercedemo.marketing.prices.application.dto;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceObjectMother {
    public static Price dummy() {
        return Price.fromPrimitive(1l, 35455l, 1l, 2l, 1,
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"),
                BigDecimal.valueOf(35.50),
                "EUR");
    }
}
