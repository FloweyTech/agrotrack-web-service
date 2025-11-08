package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.sql.Date;

@Embeddable
@Getter
public class DateRange {
    private Date startDate;
    private Date endDate;

    protected DateRange() {
    }
    public DateRange(Date startDate, Date endDate) {
        if (startDate == null)
            throw new IllegalArgumentException("startDate cannot be null");
        if (endDate == null)
            throw new IllegalArgumentException("endDate cannot be null");
        if (endDate.before(startDate))
            throw new IllegalArgumentException("endDate cannot be before startDate");
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
