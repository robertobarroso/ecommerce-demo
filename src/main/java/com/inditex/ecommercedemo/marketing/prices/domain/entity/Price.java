package com.inditex.ecommercedemo.marketing.prices.domain.entity;

import com.inditex.ecommercedemo.marketing.shared.domain.entity.BrandId;
import com.inditex.ecommercedemo.marketing.shared.domain.entity.PriceId;
import com.inditex.ecommercedemo.marketing.shared.domain.entity.PriceRateId;
import com.inditex.ecommercedemo.marketing.shared.domain.entity.ProductId;
import com.inditex.ecommercedemo.marketing.shared.domain.event.PriceCreatedDomainEvent;
import com.inditex.ecommercedemo.shared.domain.AggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Price extends AggregateRoot {
	private final PriceId id;
	private final ProductId productId;
	private final BrandId brandId;
	private final PriceRateId rateId;
	private final Priority priority;
	private final PriceDateRange dateRange;
	private final PriceValue value;
	

    public Price(PriceId id, ProductId productId, BrandId brandId, PriceRateId rateId, Priority priority, PriceDateRange dateRange, PriceValue value) {
		super();
		this.id = id;
		this.productId = productId;
		this.brandId = brandId;
		this.rateId = rateId;
		this.priority = priority;
		this.dateRange = dateRange;
		this.value = value;
	}

	// This method is not used yet. It should be used in the "Price Creation" use case.
    public static Price create(PriceId id, ProductId productId, BrandId brandId, PriceRateId priceRateId, Priority priority, PriceDateRange dateRange, PriceValue priceValue) {
        Price price = new Price(id, productId, brandId, priceRateId, priority, dateRange, priceValue);
        price.record(new PriceCreatedDomainEvent(id.value().toString(), productId.value(), brandId.value(), priceRateId.value(), priority.value(), dateRange.getStartDate().toString(), dateRange.getEndDate().toString(), priceValue.value().toString()));
        return price;
    }
    
    public static Price fromPrimitive(Long id, Long productId, Long brandId, Long rateId, int priority, LocalDateTime startDate, LocalDateTime endDate, BigDecimal value, String currency) {
    	return new Price(new PriceId(id), new ProductId(productId), new BrandId(brandId), new PriceRateId(rateId), new Priority(priority), new PriceDateRange(startDate, endDate), new PriceValue(value, currency));
    }

    public PriceId getId() {
		return id;
	}
    
	public ProductId getProductId() {
		return productId;
	}

	public BrandId getBrandId() {
		return brandId;
	}

	public PriceRateId getRateId() {
		return rateId;
	}
	
	public Priority getPriority() {
		return priority;
	}

	public PriceDateRange getDateRange() {
		return dateRange;
	}

	public PriceValue getValue() {
		return value;
	}


}
