package com.inditex.ecommercedemo.shared.domain.entity;

import com.inditex.ecommercedemo.shared.domain.criteria.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CriteriaObjectMother {

    public static Criteria ofPrice() {
        List<Filter> filters = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        filters.add(Filter.create("startDate", FilterOperator.LTE.value(), now));
        filters.add(Filter.create("endDate", FilterOperator.GT.value(), now));
        filters.add(Filter.create("productId", FilterOperator.EQUAL.value(), "35455"));
        filters.add(Filter.create("brandId", FilterOperator.EQUAL.value(), "1"));
        return new Criteria(new FilterWrapper(filters), Order.desc("priority"));
    }
}
