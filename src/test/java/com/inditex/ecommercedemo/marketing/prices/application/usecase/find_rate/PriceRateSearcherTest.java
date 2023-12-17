package com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import com.inditex.ecommercedemo.shared.domain.entity.CriteriaObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(PriceRateSearcherImpl.class)
public class PriceRateSearcherTest {

    @MockBean
    private PricePort pricePort;

    @Autowired
    private PriceRateSearcher priceRateSearcher;

    @Test
    public void testGetPrice_whenAdapterReturnResults() {
        // Prepare input
        Criteria criteria = CriteriaObjectMother.dummy();

        // Run method under test
        Price price = priceRateSearcher.search(criteria);

        // Assert expected results
        Assertions.assertNotNull(price);

    }
}
