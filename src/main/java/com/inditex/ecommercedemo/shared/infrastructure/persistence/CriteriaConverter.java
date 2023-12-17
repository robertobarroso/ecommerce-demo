package com.inditex.ecommercedemo.shared.infrastructure.persistence;

import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import com.inditex.ecommercedemo.shared.domain.criteria.Filter;
import com.inditex.ecommercedemo.shared.domain.criteria.FilterOperator;
import jakarta.persistence.criteria.*;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class CriteriaConverter<T> {

    private final CriteriaBuilder builder;

    private final HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers = new HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>>() {{
        put(FilterOperator.EQUAL, CriteriaConverter.this::equalsPredicateTransformer);
        put(FilterOperator.NOT_EQUAL, CriteriaConverter.this::notEqualsPredicateTransformer);
        put(FilterOperator.GT, CriteriaConverter.this::greaterThanPredicateTransformer);
        put(FilterOperator.GTE, CriteriaConverter.this::greaterThanOrEqualToPredicateTransformer);
        put(FilterOperator.LT, CriteriaConverter.this::lessThanPredicateTransformer);
        put(FilterOperator.LTE, CriteriaConverter.this::lessThanOrEqualToPredicateTransformer);
        put(FilterOperator.CONTAINS, CriteriaConverter.this::containsPredicateTransformer);
        put(FilterOperator.NOT_CONTAINS, CriteriaConverter.this::notContainsPredicateTransformer);
    }};
    
    private final HashMap<String, String> databaseFieldMap = new HashMap<String, String>() {{
        put("brandId", "brandId");
        put("productId", "productId");
	}};

    public CriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> aggregateClass) {
        CriteriaQuery<T> hibernateCriteria = builder.createQuery(aggregateClass);
        Root<T> root = hibernateCriteria.from(aggregateClass);

        hibernateCriteria.where(formatPredicates(criteria.filters().filters(), root));

        if (criteria.order().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.order().orderBy().value());
            Order order = criteria.order().orderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);

            hibernateCriteria.orderBy(order);
        }

        return hibernateCriteria;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        List<Predicate> predicates = filters.stream().map(filter -> formatPredicate(filter, root))
                .collect(Collectors.toList());

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);

        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        BiFunction<Filter, Root<T>, Predicate> transformer = predicateTransformers.get(filter.operator());
        return transformer.apply(filter, root);
    }

    private Predicate equalsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.equal(root.get(databaseFieldMap.get(filter.field().value())), filter.value());
    }

    private Predicate notEqualsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notEqual(root.get(databaseFieldMap.get(filter.field().value())), filter.value());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.greaterThan(root.get(databaseFieldMap.get(filter.field().value())),
                filter.value() != null ? filter.value().toString(): null);
    }

    private Predicate greaterThanOrEqualToPredicateTransformer(Filter filter, Root<T> root) {
        return builder.greaterThanOrEqualTo(root.get(databaseFieldMap.get(filter.field().value())),
                filter.value() != null ? filter.value().toString(): null);
    }

    private Predicate lessThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.lessThan(root.get(databaseFieldMap.get(filter.field().value())),
                filter.value() != null ? filter.value().toString(): null);
    }

    private Predicate lessThanOrEqualToPredicateTransformer(Filter filter, Root<T> root) {
        return builder.lessThanOrEqualTo(root.get(databaseFieldMap.get(filter.field().value())),
                filter.value() != null ? filter.value().toString(): null);
    }

    private Predicate containsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.like(root.get(databaseFieldMap.get(filter.field().value())),
                String.format("%%%s%%", filter.value()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notLike(root.get(databaseFieldMap.get(filter.field().value())),
                String.format("%%%s%%", filter.value()));
    }
}
