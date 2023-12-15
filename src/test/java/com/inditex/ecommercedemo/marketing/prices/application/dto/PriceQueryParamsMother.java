package com.inditex.ecommercedemo.marketing.prices.application.dto;

import org.springframework.util.LinkedMultiValueMap;

public class PriceQueryParamsMother {
    public static LinkedMultiValueMap<String, String> dummy() {
        LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("date", "2020-06-14T16:00:00Z");
        queryParams.add("productId", "35455");
        queryParams.add("brandId", "1");
        return queryParams;
    }
}
