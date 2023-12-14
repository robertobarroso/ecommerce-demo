package com.inditex.ecommercedemo.shared.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class DateRangeValueObject implements Serializable {
    private static final long serialVersionUID = 1L;
	final protected LocalDateTime startDate;
    final protected LocalDateTime endDate;

    public DateRangeValueObject(LocalDateTime startDate, LocalDateTime endDate) {
        ensureValidDateRange(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected DateRangeValueObject() {
    	this.startDate = null;
        this.endDate = null;
    }

    public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(endDate, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateRangeValueObject other = (DateRangeValueObject) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(startDate, other.startDate);
	}

	private void ensureValidDateRange(LocalDateTime startDate, LocalDateTime endDate) throws IllegalArgumentException {
        if (endDate.isBefore(startDate)) {
        	throw new IllegalArgumentException("endDate must be equal or greater than endDate");
        }
    }
}
