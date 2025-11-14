package com.floweytech.agrotrack.platform.reports.interfaces.rest.resources;

import java.time.LocalDate;

public record ReportResource(
        Long id,
        String status,
        Long plotId,
        Long organizationId,
        String type,
        LocalDate periodStart,
        LocalDate periodEnd,
        LocalDate generatedAt
) {
}
