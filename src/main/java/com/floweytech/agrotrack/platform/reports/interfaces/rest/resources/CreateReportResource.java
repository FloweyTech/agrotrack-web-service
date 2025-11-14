package com.floweytech.agrotrack.platform.reports.interfaces.rest.resources;


import java.time.LocalDate;

/**
 * Create a report resource.
 */
public record CreateReportResource(
        String status,
        Long plotId,
        Long organizationId,
        String type,
        LocalDate periodStart,
        LocalDate periodEnd,
        LocalDate generatedAt
) {
    /**
     * Validates the resource
     */
    public CreateReportResource {
        if ( status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }

        if ( plotId == null ) {
            throw new IllegalArgumentException("plotId is required");
        }

        if( organizationId == null ) {
            throw new IllegalArgumentException("organizationId is required");
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

        if(generatedAt == null ) {
            throw new IllegalArgumentException("generatedAt is required");
        }
    }
}
