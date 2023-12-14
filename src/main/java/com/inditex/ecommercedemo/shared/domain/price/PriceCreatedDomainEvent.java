package com.inditex.ecommercedemo.shared.domain.price;

import com.inditex.ecommercedemo.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class PriceCreatedDomainEvent extends DomainEvent {
	private final long productId;
	private final long brandId;
	private final long rateId;
	private final int priority;
	private final String startDate;
	private final String endDate;
	private final String value;

    
	public PriceCreatedDomainEvent(String aggregateId, long productId, long brandId, long rateId, int priority,
			String startDate, String endDate, String value) {
		super(aggregateId);
		this.productId = productId;
		this.brandId = brandId;
		this.rateId = rateId;
		this.priority = priority;
		this.startDate = startDate;
		this.endDate = endDate;
		this.value = value;
	}
	
    public PriceCreatedDomainEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        long productId, 
        long brandId, 
        long rateId,
        int priority,
        String startDate, 
        String endDate, 
		String value
    ) {
        super(aggregateId, eventId, occurredOn);

        this.productId = productId;
		this.brandId = brandId;
		this.rateId = rateId;
		this.priority = priority;
		this.startDate = startDate;
		this.endDate = endDate;
		this.value = value;
    }

    @Override
    public String eventName() {
        return "price.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {
			private static final long serialVersionUID = 1L;
		{
            put("productId", productId);
            put("brandId", brandId);
            put("rateId", rateId);
            put("priority", rateId);
            put("startDate", startDate);
            put("endDate", endDate);
            put("value", value);
        }};
    }

    @Override
    public PriceCreatedDomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new PriceCreatedDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (long) body.get("productId"),
            (long) body.get("brandId"),
            (long) body.get("rateId"),
            (int) body.get("priority"),
            (String) body.get("startDate"),
            (String) body.get("endDate"),
            (String) body.get("value")
        );
    }



    public long getProductId() {
		return productId;
	}

	public long getBrandId() {
		return brandId;
	}

	public long getRateId() {
		return rateId;
	}

	public long getPriority() {
		return priority;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandId, endDate, productId, rateId, startDate, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceCreatedDomainEvent other = (PriceCreatedDomainEvent) obj;
		return brandId == other.brandId && Objects.equals(endDate, other.endDate) && productId == other.productId
				&& rateId == other.rateId && Objects.equals(startDate, other.startDate)
				&& Objects.equals(value, other.value);
	}

	
}
