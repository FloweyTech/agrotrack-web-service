package com.floweytech.agrotrack.platform.reports.interfaces.rest.resources;


import java.time.LocalDate;

/**
 * Create Report Resource
 * @summary
 * Represents the data transfer object (Resource) required to initiate the creation of a new report.
 * It encapsulates the user's request details, including the report scope (type), the specific
 * environmental metric to analyze, and the time period for the analysis.
 *
 * @param type The type of report (e.g., PARCEL, GENERAL).
 * @param metricType The environmental metric to analyze (e.g., TEMPERATURE, HUMIDITY).
 * @param periodStart The start date of the report period.
 * @param periodEnd The end date of the report period.
 *
 * @author FloweyTech developer team
 */
public record CreateReportResource(
        String type,
        String metricType,
        LocalDate periodStart,
        LocalDate periodEnd
) {
    /**
     * Compact Constructor with Validation
     * @summary
     * Validates the resource data upon instantiation.
     * It ensures that all mandatory fields are present and that the provided date range
     * is logically valid (the end date cannot be before the start date).
     *
     * @throws IllegalArgumentException If any required field is missing or if the date range is invalid.
     */
    public CreateReportResource {

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
