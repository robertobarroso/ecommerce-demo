package com.inditex.ecommercedemo.shared.domain;

import java.util.Objects;

public abstract class LongValueObject {
    private Long value;

    public LongValueObject(Long value) {
        this.value = value;
    }

    public LongValueObject() {
        this.value = null;
    }
    
    public Long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LongValueObject that = (LongValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
