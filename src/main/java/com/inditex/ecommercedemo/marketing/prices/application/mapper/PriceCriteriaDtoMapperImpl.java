package com.inditex.ecommercedemo.marketing.prices.application.mapper;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceCriteriaDto;
import com.inditex.ecommercedemo.shared.domain.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceCriteriaDtoMapperImpl implements PriceCriteriaDtoMapper {

    @Override
    public Criteria toDomain(PriceCriteriaDto priceCriteriaDto) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.create("startDate", FilterOperator.LTE.value(), priceCriteriaDto.getDate()));
        filters.add(Filter.create("endDate", FilterOperator.GT.value(), priceCriteriaDto.getDate()));
        filters.add(Filter.create("productId", FilterOperator.EQUAL.value(), priceCriteriaDto.getProductId()));
        filters.add(Filter.create("brandId", FilterOperator.EQUAL.value(), priceCriteriaDto.getBrandId()));
        return new Criteria(new FilterWrapper(filters), Order.desc("priority"));
    }
}
