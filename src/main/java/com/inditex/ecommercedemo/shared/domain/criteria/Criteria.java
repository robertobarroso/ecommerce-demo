package com.inditex.ecommercedemo.shared.domain.criteria;

import java.util.Optional;

public final class Criteria {
    private final FilterWrapper filterWrapper;
    private final Order order;
    private final Optional<Integer> limit;
    private final Optional<Integer> offset;

    public Criteria(FilterWrapper filterWrapper, Order order, Optional<Integer> limit, Optional<Integer> offset) {
        this.filterWrapper = filterWrapper;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
    }

    public Criteria(FilterWrapper filterWrapper, Order order) {
        this.filterWrapper = filterWrapper;
        this.order = order;
        this.limit = Optional.empty();
        this.offset = Optional.empty();
    }

    public FilterWrapper filterWrapper() {
        return filterWrapper;
    }

    public Order order() {
        return order;
    }

    public Optional<Integer> limit() {
        return limit;
    }

    public Optional<Integer> offset() {
        return offset;
    }

    public boolean hasFilters() {
        return filterWrapper.filters().size() > 0;
    }

    public String serialize() {
        return String.format(
            "%s~~%s~~%s~~%s",
            filterWrapper.serialize(),
            order.serialize(),
            offset.orElse(0),
            limit.orElse(0)
        );
    }
}
