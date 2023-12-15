package com.inditex.ecommercedemo.marketing.prices.domain.entity;

import com.inditex.ecommercedemo.shared.domain.DateRangeValueObject;

import java.time.LocalDateTime;


public final class PriceDateRange extends DateRangeValueObject {
	private static final long serialVersionUID = 1L;

	public PriceDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        super(startDate, endDate);
    }

    public PriceDateRange() {
    }
}