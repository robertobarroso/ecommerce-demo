package com.inditex.ecommercedemo.shared.domain;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public abstract class MoneyValueObject {
    private BigDecimal value;
    private Currency currency;
    public MoneyValueObject(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal value() {
        return value;
    }
    
    public Currency currency() {
    	return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoneyValueObject that = (MoneyValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
