package com.inditex.ecommercedemo.marketing.prices.infrastructure.adapter;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.marketing.prices.infrastructure.entity.PriceEntity;
import com.inditex.ecommercedemo.marketing.prices.infrastructure.entity.PriceEntityObjectMother;
import com.inditex.ecommercedemo.marketing.prices.infrastructure.repository.PriceRepository;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import com.inditex.ecommercedemo.shared.domain.entity.CriteriaObjectMother;
import com.inditex.ecommercedemo.shared.domain.exception.DatabaseException;
import com.inditex.ecommercedemo.shared.infrastructure.persistence.CriteriaConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(PriceAdapter.class)
public class PriceAdapterTest {

    @Autowired
    private PricePort pricePort;

    @MockBean
    private CriteriaConverter<PriceEntity> criteriaConverter;

    @MockBean
    private PriceRepository priceRepository;

    @Test
    public void testGetPriceList_whenRepositoryReturnResults() {
        // Prepare data
        Criteria criteria = CriteriaObjectMother.ofPrice();
        List<PriceEntity> pricesDb = PriceEntityObjectMother.dummyList();
        when(criteriaConverter.convert(ArgumentMatchers.any(Criteria.class)))
                .thenReturn((root, criteriaQuery, criteriaBuilder) -> criteriaQuery.getRestriction());
        when(priceRepository.findAll(ArgumentMatchers.any(Specification.class))).thenReturn(pricesDb);

        // Run method under test
        List<Price> prices = this.pricePort.searchByCriteria(criteria);

        // Assert results are not empty
        Assertions.assertNotEquals(List.of(), prices);
    }

    @Test
    public void testGetError_whenRepositoryThrowDataAccessException() {
        // Prepare data
        Criteria criteria = CriteriaObjectMother.ofPrice();
        List<PriceEntity> pricesDb = PriceEntityObjectMother.dummyList();
        when(criteriaConverter.convert(ArgumentMatchers.any(Criteria.class)))
                .thenReturn((root, criteriaQuery, criteriaBuilder) -> criteriaQuery.getRestriction());
        when(priceRepository.findAll(ArgumentMatchers.any(Specification.class)))
                .thenThrow(new InvalidDataAccessResourceUsageException("bad SQL description"));

        // Run method under test
        Assertions.assertThrows(DatabaseException.class, () -> this.pricePort.searchByCriteria(criteria));
    }
}
