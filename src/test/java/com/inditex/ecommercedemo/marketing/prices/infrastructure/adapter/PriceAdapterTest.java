package com.inditex.ecommercedemo.marketing.prices.infrastructure.adapter;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import com.inditex.ecommercedemo.shared.domain.entity.CriteriaObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@Import(PriceAdapter.class)
public class PriceAdapterTest {

    @Autowired
    private PricePort pricePort;

    @Test
    public void testGetPriceList_whenRepositoryReturnResults() {
        // Prepare data
        Criteria criteria = CriteriaObjectMother.ofPrice();

        // Run method under test
        List<Price> prices = this.pricePort.searchByCriteria(criteria);

        // Assert results are not empty
        Assertions.assertNotEquals(List.of(), prices);
    }
}
