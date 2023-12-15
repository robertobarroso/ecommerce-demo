package com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import org.springframework.stereotype.Service;

@Service
public class PriceRateSearcherImpl implements PriceRateSearcher {

    private final PricePort pricePort;

    public PriceRateSearcherImpl(PricePort pricePort) {
        this.pricePort = pricePort;
    }

    @Override
    public Price search(Criteria criteria) {
        return null;
    }
}
