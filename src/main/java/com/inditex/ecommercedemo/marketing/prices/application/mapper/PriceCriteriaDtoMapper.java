package com.inditex.ecommercedemo.marketing.prices.application.mapper;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceCriteriaDto;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;

public interface PriceCriteriaDtoMapper {
    Criteria toDomain(PriceCriteriaDto priceCriteriaDto);
}
