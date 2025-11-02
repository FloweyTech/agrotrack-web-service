package com.floweytech.agrotrack.platform.organization.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlotCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SizeArea;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.resources.CreatePlotResource;

public class CreatePlotCommandFromResourceAssembler {

    public static CreatePlotCommand toCommandFromResource(CreatePlotResource resource) {
        return new CreatePlotCommand(
            new PlotId(resource.plotId()),
            resource.plotName(),
            new SizeArea(resource.size(), resource.unit()),
            new PlantTypeId(resource.plantTypeId()),
            resource.location(),
            new OrganizationId(resource.organizationId())
        );
    }
}

