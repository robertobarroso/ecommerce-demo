package com.inditex.ecommercedemo.marketing.prices.domain.port;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;

import java.util.List;

public interface PricePort {
    List<Price> searchByCriteria(Criteria criteria);
}
