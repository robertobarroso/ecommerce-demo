package com.inditex.ecommercedemo.marketing.prices.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PriceEntityObjectMother {
    public static List<PriceEntity> dummyList() {
        PriceEntity priceEntity1 = new PriceEntity();
        priceEntity1.setId(1l);
        priceEntity1.setProductId(354551l);
        priceEntity1.setBrandId(1l);
        priceEntity1.setRateId(1l);
        priceEntity1.setPriority(0);
        priceEntity1.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        priceEntity1.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        priceEntity1.setPrice(BigDecimal.valueOf(35.50));
        priceEntity1.setCurrency("EUR");

        PriceEntity priceEntity2 = new PriceEntity();
        priceEntity2.setId(2l);
        priceEntity2.setProductId(354551l);
        priceEntity2.setBrandId(1l);
        priceEntity2.setRateId(2l);
        priceEntity2.setPriority(1);
        priceEntity2.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00"));
        priceEntity2.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00"));
        priceEntity2.setPrice(BigDecimal.valueOf(25.45));
        priceEntity2.setCurrency("EUR");

        PriceEntity priceEntity3 = new PriceEntity();
        priceEntity3.setId(3l);
        priceEntity3.setProductId(354551l);
        priceEntity3.setBrandId(1l);
        priceEntity3.setRateId(3l);
        priceEntity3.setPriority(1);
        priceEntity3.setStartDate(LocalDateTime.parse("2020-06-15T00:00:00"));
        priceEntity3.setEndDate(LocalDateTime.parse("2020-06-15T11:00:00"));
        priceEntity3.setPrice(BigDecimal.valueOf(30.50));
        priceEntity3.setCurrency("EUR");

        PriceEntity priceEntity4 = new PriceEntity();
        priceEntity4.setId(4l);
        priceEntity4.setProductId(354551l);
        priceEntity4.setBrandId(1l);
        priceEntity4.setRateId(4l);
        priceEntity4.setPriority(1);
        priceEntity4.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
        priceEntity4.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        priceEntity4.setPrice(BigDecimal.valueOf(38.95));
        priceEntity4.setCurrency("EUR");

        return List.of(priceEntity1, priceEntity2, priceEntity3, priceEntity4);
    }
}
