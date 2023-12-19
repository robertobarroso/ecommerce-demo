package com.inditex.ecommercedemo.marketing.prices.infrastructure.repository;

import com.inditex.ecommercedemo.marketing.prices.infrastructure.entity.PriceEntity;
import com.inditex.ecommercedemo.shared.domain.entity.CriteriaObjectMother;
import com.inditex.ecommercedemo.shared.infrastructure.persistence.CriteriaConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


@DataJpaTest
@Import(CriteriaConverter.class)
public class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    CriteriaConverter criteriaConverter;

    @Test
    public void testFindPrices_whenCriteriaMatch() {
        // Prepare data
        Specification<PriceEntity> specification = this.criteriaConverter.convert(CriteriaObjectMother.ofPrice());

        // Run method under test
        List<PriceEntity> prices = this.priceRepository.findAll(specification);

        // Assert results are not empty
        Assertions.assertNotEquals(List.of(), prices);
        Assertions.assertEquals(2, prices.size());
    }
}
