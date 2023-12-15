package com.inditex.ecommercedemo.marketing.prices.domain.entity;

import com.inditex.ecommercedemo.marketing.shared.domain.BrandId;
import com.inditex.ecommercedemo.marketing.shared.domain.PriceId;
import com.inditex.ecommercedemo.marketing.shared.domain.PriceRateId;
import com.inditex.ecommercedemo.marketing.shared.domain.ProductId;
import com.inditex.ecommercedemo.shared.domain.AggregateRoot;
import com.inditex.ecommercedemo.shared.domain.price.PriceCreatedDomainEvent;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public final class Price extends AggregateRoot {
	private final PriceId id;
	private final ProductId productId;
	private final BrandId brandId;
	private final PriceRateId prieRateId;
	private final Priority priority;
	private final PriceDateRange priceDateRange;
	private final PriceValue value;
	

    public Price(PriceId id, ProductId productId, BrandId brandId, PriceRateId prieRateId, Priority priority, PriceDateRange priceDateRange, PriceValue value) {
		super();
		this.id = id;
		this.productId = productId;
		this.brandId = brandId;
		this.prieRateId = prieRateId;
		this.priority = priority;
		this.priceDateRange = priceDateRange;
		this.value = value;
	}

    public static Price create(PriceId id, ProductId productId, BrandId brandId, PriceRateId priceRateId, Priority priority, PriceDateRange priceDateRange, PriceValue priceValue) {
        Price price = new Price(id, productId, brandId, priceRateId, priority, priceDateRange, priceValue);
        price.record(new PriceCreatedDomainEvent(id.value().toString(), productId.value(), brandId.value(), priceRateId.value(), priority.value(), priceDateRange.getStartDate().toString(), priceDateRange.getEndDate().toString(), priceValue.value().toString()));
        return price;
    }
    
    public static Price fromPrimitive(Long id, Long productId, Long brandId, Long priceRateId, int priority, LocalDateTime startDate, LocalDateTime endDate, BigDecimal value, String currency) {
    	return new Price(new PriceId(id), new ProductId(productId), new BrandId(brandId), new PriceRateId(priceRateId), new Priority(priority), new PriceDateRange(startDate, endDate), new PriceValue(value, currency));
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

	public PriceRateId getPriceRateId() {
		return prieRateId;
	}
	
	public Priority getPriority() {
		return priority;
	}

	public PriceDateRange getPriceDateRange() {
		return priceDateRange;
	}

	public PriceValue getValue() {
		return value;
	}


}
