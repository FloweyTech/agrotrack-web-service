package com.floweytech.agrotrack.platform.reports.interfaces.rest.resources;


import java.time.LocalDate;

/**
 * Create a report resource.
 */
public record CreateReportResource(
        String status,
        String type,
        LocalDate periodStart,
        LocalDate periodEnd
) {
    /**
     * Validates the resource
     */
    public CreateReportResource {
        if ( status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }

        if( type == null || type.isBlank()) {
            throw new IllegalArgumentException("type is required");
        }

        if( periodStart == null) {
            throw new IllegalArgumentException("periodStart is required");
        }

        if(periodEnd == null ) {
            throw new IllegalArgumentException("periodEnd is required");
        }

        if (periodEnd.isBefore(periodStart)) {
            throw new IllegalArgumentException("periodEnd cannot be before periodStart");
        }

    }
}
