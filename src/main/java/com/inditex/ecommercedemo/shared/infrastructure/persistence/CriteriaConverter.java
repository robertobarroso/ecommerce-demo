package com.inditex.ecommercedemo.shared.infrastructure.persistence;

import com.inditex.ecommercedemo.shared.domain.TriFunction;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import com.inditex.ecommercedemo.shared.domain.criteria.Filter;
import com.inditex.ecommercedemo.shared.domain.criteria.FilterOperator;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Component
public final class CriteriaConverter<T> {

    private final HashMap<FilterOperator, TriFunction<Filter<?>, Root<T>, CriteriaBuilder, Predicate>> predicateTransformers =
            new HashMap<>() {{
        put(FilterOperator.EQUAL, CriteriaConverter.this::equalsPredicateTransformer);
        put(FilterOperator.NOT_EQUAL, CriteriaConverter.this::notEqualsPredicateTransformer);
        put(FilterOperator.GT, CriteriaConverter.this::greaterThanPredicateTransformer);
        put(FilterOperator.GTE, CriteriaConverter.this::greaterThanOrEqualToPredicateTransformer);
        put(FilterOperator.LT, CriteriaConverter.this::lessThanPredicateTransformer);
        put(FilterOperator.LTE, CriteriaConverter.this::lessThanOrEqualToPredicateTransformer);
        put(FilterOperator.CONTAINS, CriteriaConverter.this::containsPredicateTransformer);
        put(FilterOperator.NOT_CONTAINS, CriteriaConverter.this::notContainsPredicateTransformer);
    }};
    
    public Specification<T> convert(Criteria criteria) {
         return (Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            cq.where(formatPredicates(criteria.filterWrapper().filters(), root, cb));
            if (criteria.order().hasOrder()) {
                Path<Object> orderBy = root.get(criteria.order().orderBy().value());
                Order order = criteria.order().orderType().isAsc() ? cb.asc(orderBy) : cb.desc(orderBy);
                cq.orderBy(order);
            }

            return cq.getRestriction();
        };
    }

    private Predicate[] formatPredicates(List<Filter<?>> filters, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return filters.stream().map(filter -> formatPredicate(filter, root, criteriaBuilder))
                .toList().toArray(new Predicate[0]);
    }

    private Predicate formatPredicate(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        TriFunction<Filter<?>, Root<T>, CriteriaBuilder, Predicate> transformer = predicateTransformers.get(filter.operator());
        return transformer.apply(filter, root, criteriaBuilder);
    }

    private Predicate equalsPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(filter.field().value()), filter.value());
    }

    private Predicate notEqualsPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.notEqual(root.get(filter.field().value()), filter.value());
    }

    private Predicate greaterThanPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(root.get(filter.field().value()),
                filter.value() != null ? (LocalDateTime) filter.value() : null);

    }

    private Predicate greaterThanOrEqualToPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get(filter.field().value()),
            filter.value() != null ? (LocalDateTime) filter.value() : null);
    }

    private Predicate lessThanPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lessThan(root.get(filter.field().value()),
                filter.value() != null ? (LocalDateTime) filter.value(): null);
    }

    private Predicate lessThanOrEqualToPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lessThanOrEqualTo(root.get(filter.field().value()),
                filter.value() != null ? (LocalDateTime) filter.value() : null);
    }

    private Predicate containsPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get(filter.field().value()), String.format("%%%s%%", filter.value()));
    }

    private Predicate notContainsPredicateTransformer(Filter<?> filter, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.notLike(root.get(filter.field().value()), String.format("%%%s%%", filter.value()));
    }
}
