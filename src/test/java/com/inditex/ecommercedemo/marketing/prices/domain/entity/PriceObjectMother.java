package com.inditex.ecommercedemo.marketing.prices.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PriceObjectMother {
    public static Price dummy() {
        return Price.fromPrimitive(1l, 35455l, 1l, 2l, 1,
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"),
                BigDecimal.valueOf(35.50),
                "EUR");
    }

    public static List<Price> dummyList() {
        return List.of(
                Price.fromPrimitive(1l, 35455l, 1l, 1l, 0,
                    LocalDateTime.parse("2020-06-14T00:00:00"),
                    LocalDateTime.parse("2020-12-31T23:59:59"),
                    BigDecimal.valueOf(35.50),
                    "EUR"),
                Price.fromPrimitive(2l, 35455l, 1l, 2l, 1,
                    LocalDateTime.parse("2020-06-14T15:00:00"),
                    LocalDateTime.parse("2020-06-14T18:30:00"),
                    BigDecimal.valueOf(25.45),
                    "EUR"),
                Price.fromPrimitive(3l, 35455l, 1l, 3l, 1,
                    LocalDateTime.parse("2020-06-15T00:00:00"),
                    LocalDateTime.parse("2020-06-15T11:00:00"),
                    BigDecimal.valueOf(30.50),
                    "EUR"),
                Price.fromPrimitive(4l, 35455l, 1l, 4l, 1,
                    LocalDateTime.parse("2020-06-15T16:00:00"),
                    LocalDateTime.parse("2020-12-31T23:59:59"),
                    BigDecimal.valueOf(38.95),
                    "EUR"));
    }
}
