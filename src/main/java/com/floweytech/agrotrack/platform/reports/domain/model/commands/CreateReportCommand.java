package com.floweytech.agrotrack.platform.reports.domain.model.commands;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlotId;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportStatus;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportType;

import java.time.LocalDate;

/**
 * Create Report Command
 * @summary
 * CreateReportCommand is a record class that represents the command to create a report.
 */
public record CreateReportCommand(
        ReportStatus status,
        PlotId plotId,
        OrganizationId organizationId,
        ReportType type,
        LocalDate periodStart,
        LocalDate periodEnd,
        LocalDate generatedAt
) {
    /**
     * Validates the command
     */
    public CreateReportCommand {
        if(status == null )
            throw new IllegalArgumentException("status cannot be null");
        if(plotId == null )
            throw new IllegalArgumentException("plotId cannot be null");
        if(organizationId == null )
            throw new IllegalArgumentException("organizationId cannot be null");
        if(type == null)
            throw new IllegalArgumentException("type cannot be null");
        if(periodStart == null )
            throw new IllegalArgumentException("periodStart cannot be null");
        if(periodEnd == null )
            throw new IllegalArgumentException("periodEnd cannot be null");
        if(generatedAt == null )
            throw new IllegalArgumentException("generatedAt cannot be null");
    }


}
