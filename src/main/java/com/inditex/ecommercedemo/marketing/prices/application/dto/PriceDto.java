package com.inditex.ecommercedemo.marketing.prices.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PriceDto {
	@JsonProperty
	private final Long id;

	@JsonProperty
	private final Long productId;

	@JsonProperty
	private final Long brandId;

	@JsonProperty
	private final Long rateId;

	@JsonProperty
	private final LocalDateTime startDate;

	@JsonProperty
	private final LocalDateTime endDate;

	@JsonProperty
	private final String value;

	@JsonProperty
	private final String currency;
	
	public static PriceDto fromAggregate(Price price) {
        return new PriceDto(price.getId().value(), price.getProductId().value(), price.getBrandId().value(), price.getRateId().value(), price.getDateRange().getStartDate(), price.getDateRange().getEndDate(), price.getValue().value().toString(), price.getValue().currency().toString());
    }
}
