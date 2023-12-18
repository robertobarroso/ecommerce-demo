package com.inditex.ecommercedemo.shared.domain.criteria;

import java.util.HashMap;

public final class Filter {
    private final FilterField    field;
    private final FilterOperator operator;
    private final Object    value;

    public Filter(FilterField field, FilterOperator operator, Object value) {
        this.field    = field;
        this.operator = operator;
        this.value    = value;
    }

    public static Filter create(String field, String operator, Object value) {
        return new Filter(
            new FilterField(field),
            FilterOperator.fromValue(operator.toUpperCase()),
            value
        );
    }

    public static Filter create(HashMap<String, String> values) {
        return new Filter(
            new FilterField(values.get("field")),
            FilterOperator.fromValue(values.get("operator")),
            values.get("value")
        );
    }

    public FilterField field() {
        return field;
    }

    public FilterOperator operator() {
        return operator;
    }

    public Object value() {
        return value;
    }

    public String serialize() {
        return String.format("%s.%s.%s", field.value(), operator.value(), value);
    }


}
