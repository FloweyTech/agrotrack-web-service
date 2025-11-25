package com.floweytech.agrotrack.platform.reports.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReportResource(
        Long id,
        String status,
        Long plotId,
        Long organizationId,
        String type,
        LocalDate periodStart,
        LocalDate periodEnd,
        LocalDateTime generatedAt
) {
}
