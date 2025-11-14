package com.floweytech.agrotrack.platform.reports.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlotId;
import com.floweytech.agrotrack.platform.reports.domain.model.commands.CreateReportCommand;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportStatus;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportType;
import com.floweytech.agrotrack.platform.reports.interfaces.rest.resources.CreateReportResource;

/**
 * Assembler to convert a CreateReportResource to a CreateReportCommand
 */
public class CreateReportCommandFromResourceAssembler {

    public static CreateReportCommand toCommandFromResource( CreateReportResource resource ) {
        return new CreateReportCommand(
                ReportStatus.valueOf(resource.status()),
                 new PlotId(resource.plotId()),
                 new OrganizationId(resource.organizationId()),
               ReportType.valueOf(resource.type()),
                resource.periodStart(),
                resource.periodEnd(),
                resource.generatedAt());
    }

}
