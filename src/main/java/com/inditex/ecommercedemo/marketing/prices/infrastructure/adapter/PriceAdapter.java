package com.inditex.ecommercedemo.marketing.prices.infrastructure.adapter;

import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.marketing.prices.domain.port.PricePort;
import com.inditex.ecommercedemo.marketing.prices.infrastructure.entity.PriceEntity;
import com.inditex.ecommercedemo.marketing.prices.infrastructure.repository.PriceRepository;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import com.inditex.ecommercedemo.shared.domain.exception.DatabaseException;
import com.inditex.ecommercedemo.shared.infrastructure.persistence.CriteriaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceAdapter implements PricePort {

    private PriceRepository priceRepository;

    @Autowired
    private CriteriaConverter<PriceEntity> criteriaConverter;

    public PriceAdapter(PriceRepository priceRepository, CriteriaConverter criteriaConverter) {
        this.priceRepository = priceRepository;
        this.criteriaConverter = criteriaConverter;
    }

    @Override
    public List<Price> searchByCriteria(Criteria criteria) {
        try {
            return priceRepository.findAll(criteriaConverter.convert(criteria))
                    .stream()
                    .map(PriceEntity::toAggregate)
                    .collect(Collectors.toList());

        } catch (DataAccessException e) {
            throw new DatabaseException("Price Repository", e);
        }
    }
}
