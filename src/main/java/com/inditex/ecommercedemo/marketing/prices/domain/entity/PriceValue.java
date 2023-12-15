package com.inditex.ecommercedemo.marketing.prices.domain.entity;

import com.inditex.ecommercedemo.shared.domain.MoneyValueObject;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;


public final class PriceValue extends MoneyValueObject {
    public PriceValue(String value, String currency) {
        super(new BigDecimal(value), Currency.getInstance(currency));
    }
    
    public PriceValue(BigDecimal value, String currency) {
        super(value, Currency.getInstance(currency));
    }
        
    public PriceValue(String value, Currency currency) {
        super(new BigDecimal(value), currency);
    }
    
    public PriceValue(String value) {
        super(new BigDecimal(value), Currency.getInstance(Locale.getDefault()));
    }

}