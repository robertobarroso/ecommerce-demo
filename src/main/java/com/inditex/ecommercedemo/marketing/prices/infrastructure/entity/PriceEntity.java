package com.inditex.ecommercedemo.marketing.prices.infrastructure.entity;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
public class PriceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRAND_ID")
    private Long brandId;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "RATE_ID")
    private Long rateId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURR")
    private String currency;

    public Price toAggregate() {
    	return Price.fromPrimitive(id, 
    			productId, 
    			brandId, 
    			rateId, 
    			priority,
    			startDate,
    			endDate,
    			price, 
    			currency);
    }
}