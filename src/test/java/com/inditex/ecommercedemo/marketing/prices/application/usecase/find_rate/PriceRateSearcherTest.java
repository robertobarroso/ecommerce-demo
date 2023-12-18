package com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceObjectMother;
import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import com.inditex.ecommercedemo.shared.domain.entity.CriteriaObjectMother;
import com.inditex.ecommercedemo.shared.domain.exception.DatabaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(PriceRateSearcherImpl.class)
public class PriceRateSearcherTest {

    @Autowired
    private PriceRateSearcher priceRateSearcher;

    @MockBean
    private PricePort pricePort;

    @Test
    public void testGetPrice_whenAdapterReturnResults() {
        // Prepare data
        Criteria criteria = CriteriaObjectMother.ofPrice();
        List<Price> pricesDb = PriceObjectMother.dummyList();
        when(pricePort.searchByCriteria(ArgumentMatchers.any(Criteria.class))).thenReturn(pricesDb);

        // Run method under test
        Optional<Price> price = priceRateSearcher.search(criteria);

        // Assert Price is present and rateId property has a value
        Assertions.assertTrue(price.isPresent());
        Assertions.assertNotNull(price.get().getRateId().value());
    }

    @Test
    public void testGetEmptyPrice_whenAdapterReturnNoResults() {
        // Prepare data
        Criteria criteria = CriteriaObjectMother.ofPrice();
        when(pricePort.searchByCriteria(ArgumentMatchers.any(Criteria.class))).thenReturn(List.of());

        // Run method under test
        Optional<Price> price = priceRateSearcher.search(criteria);

        // Assert Price is present and rateId property has a value
        Assertions.assertTrue(price.isEmpty());
    }

    @Test
    public void testGetError_whenASystemExceptionIsThrown() {
        // Prepare data
        Criteria criteria = CriteriaObjectMother.ofPrice();
        when(pricePort.searchByCriteria(ArgumentMatchers.any(Criteria.class))).thenThrow(new DatabaseException("PriceRepository", null));

        // Run method under test
        Assertions.assertThrows(DatabaseException.class, () -> priceRateSearcher.search(criteria));
    }
}
