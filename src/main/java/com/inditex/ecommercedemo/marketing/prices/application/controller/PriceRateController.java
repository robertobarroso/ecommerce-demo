package com.inditex.ecommercedemo.marketing.prices.application.controller;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceCriteriaDto;
import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceDto;
import com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate.PriceRateSearcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceRateController {
    private final PriceRateSearcher priceRateSearcher;

    public PriceRateController(PriceRateSearcher priceRateSearcher) {
        this.priceRateSearcher = priceRateSearcher;
    }

    @GetMapping("/rates")
    public ResponseEntity<PriceDto> searchPriceRate(PriceCriteriaDto priceCriteriaDto) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
