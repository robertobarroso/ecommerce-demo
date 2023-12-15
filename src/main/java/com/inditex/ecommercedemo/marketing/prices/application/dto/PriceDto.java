package com.inditex.ecommercedemo.marketing.prices.application.dto;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;

import java.time.LocalDateTime;

public class PriceDto {
	private final Long id;
	private final Long productId;
	private final Long brandId;
	private final Long rateId;
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;
	private final String value;
	private final String currency;
	
	public PriceDto(Long id, Long productId, Long brandId, Long rateId, LocalDateTime startDate, LocalDateTime endDate,
			String value, String currency) {
		super();
		this.id = id;
		this.productId = productId;
		this.brandId = brandId;
		this.rateId = rateId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.value = value;
		this.currency = currency;
	}

	public static PriceDto fromAggregate(Price price) {
        return new PriceDto(price.getId().value(), price.getProductId().value(), price.getBrandId().value(), price.getPriceRateId().value(), price.getPriceDateRange().getStartDate(), price.getPriceDateRange().getEndDate(), price.getValue().value().toString(), price.getValue().currency().toString());
    }
	
	public Long getId() {
		return id;
	}
	
	public Long getProductId() {
		return productId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public Long getRateId() {
		return rateId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public String getValue() {
		return value;
	}

	public String getCurrency() {
		return currency;
	}
	
	
}
