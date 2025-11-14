package com.floweytech.agrotrack.platform.reports.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.reports.domain.model.aggregates.Report;
import com.floweytech.agrotrack.platform.reports.interfaces.rest.resources.ReportResource;

/**
 * Assembler to convert a Report entity to a ReportResource.
 */
public class ReportResourceFromEntityAssembler {

    public static ReportResource toResourceFromEntity(Report entity) {
        return new ReportResource(
                entity.getId(),
                entity.getStatus().name(),
                entity.getPlotId().value(),
                entity.getOrganizationId().value(),
                entity.getType().name(),
                entity.getPeriodStart(),
                entity.getPeriodEnd(),
                entity.getGeneratedAt()
        );
    }
}
