package com.inditex.ecommercedemo.shared.domain.criteria;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class FilterWrapper {
    private final List<Filter> filters;

    public FilterWrapper(List<Filter> filters) {
        this.filters = filters;
    }

    public static FilterWrapper create(List<HashMap<String, String>> filters) {
        return new FilterWrapper(filters.stream().map(Filter::create).collect(Collectors.toList()));
    }

    public static FilterWrapper none() {
        return new FilterWrapper(Collections.emptyList());
    }

    public List<Filter> filters() {
        return filters;
    }

    public String serialize() {
        return filters.stream().map(Filter::serialize).collect(Collectors.joining("^"));
    }
}
