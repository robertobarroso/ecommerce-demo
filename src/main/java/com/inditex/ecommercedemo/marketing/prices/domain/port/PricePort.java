package com.inditex.ecommercedemo.marketing.prices.domain.port;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;

public interface PricePort {
    Price searchByCriteria(Criteria criteria);
}
