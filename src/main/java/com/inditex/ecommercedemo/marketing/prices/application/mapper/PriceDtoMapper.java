package com.inditex.ecommercedemo.marketing.prices.application.mapper;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceDto;
import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {
    @Mapping(expression="java(price.getId().value())", target="id")
    @Mapping(expression="java(price.getBrandId().value())", target="brandId")
    @Mapping(expression="java(price.getProductId().value())", target="productId")
    @Mapping(expression="java(price.getDateRange().getStartDate())", target="startDate")
    @Mapping(expression="java(price.getDateRange().getEndDate())", target="endDate")
    @Mapping(expression="java(price.getRateId().value())", target="rateId")
    @Mapping(expression="java(price.getValue().value().toString())", target="value")
    @Mapping(expression="java(price.getValue().currency().toString())", target="currency")
    PriceDto toDto(Price price);
}
