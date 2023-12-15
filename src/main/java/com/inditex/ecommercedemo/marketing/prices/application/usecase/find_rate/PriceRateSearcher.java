package com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;

public interface PriceRateSearcher {

    Price search(Criteria criteria);

}
