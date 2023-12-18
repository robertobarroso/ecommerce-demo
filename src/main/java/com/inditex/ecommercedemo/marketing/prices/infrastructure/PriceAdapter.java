package com.inditex.ecommercedemo.marketing.prices.infrastructure;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAdapter implements PricePort {

    @Override
    public List<Price> searchByCriteria(Criteria criteria) {
        return null;
    }
}
