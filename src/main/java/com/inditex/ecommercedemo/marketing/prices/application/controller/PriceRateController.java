package com.inditex.ecommercedemo.marketing.prices.application.controller;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceCriteriaDto;
import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceDto;
import com.inditex.ecommercedemo.marketing.prices.application.mapper.PriceCriteriaDtoMapper;
import com.inditex.ecommercedemo.marketing.prices.application.mapper.PriceDtoMapper;
import com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate.PriceRateSearcher;
import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceRateController {

    private final PriceRateSearcher priceRateSearcher;

    private final PriceCriteriaDtoMapper priceCriteriaDtoMapper;

    private final PriceDtoMapper priceDtoMapper;

    public PriceRateController(PriceRateSearcher priceRateSearcher, PriceCriteriaDtoMapper priceCriteriaDtoMapper, PriceDtoMapper priceDtoMapper) {
        this.priceRateSearcher = priceRateSearcher;
        this.priceCriteriaDtoMapper = priceCriteriaDtoMapper;
        this.priceDtoMapper = priceDtoMapper;
    }

    @GetMapping("/rates")
    public ResponseEntity<PriceDto> searchPriceRate(@Valid PriceCriteriaDto priceCriteriaDto) {
        Criteria criteria = priceCriteriaDtoMapper.toDomain(priceCriteriaDto);
        Price price = priceRateSearcher.search(criteria);
        PriceDto priceDto = this.priceDtoMapper.toDto(price);
        return ResponseEntity.ok(this.priceDtoMapper.toDto(price));
    }
}
