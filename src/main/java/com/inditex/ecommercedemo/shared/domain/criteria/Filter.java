package com.inditex.ecommercedemo.shared.domain.criteria;

public final class Filter<T> {
    private final FilterField field;
    private final FilterOperator operator;
    private final T value;

    public Filter(FilterField field, FilterOperator operator, T value) {
        this.field    = field;
        this.operator = operator;
        this.value    = value;
    }

    public static <T> Filter create(String field, String operator, T value) {
        return new Filter(
            new FilterField(field),
            FilterOperator.fromValue(operator.toUpperCase()),
            value
        );
    }

    public FilterField field() {
        return field;
    }

    public FilterOperator operator() {
        return operator;
    }

    public T value() {
        return value;
    }

    public String serialize() {
        return String.format("%s.%s.%s", field.value(), operator.value(), value);
    }
}
