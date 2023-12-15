package com.inditex.ecommercedemo.marketing.prices.infrastructure;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;

public class PriceAdapter implements PricePort {

    @Override
    public Price findByCriteriaMaxPriority(Criteria criteria) {
        return null;
    }
}
