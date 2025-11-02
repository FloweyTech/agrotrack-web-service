package com.floweytech.agrotrack.platform.organization.domain.model.commands;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SizeArea;

public record CreatePlotCommand(
    PlotId plotId,
    String plotName,
    SizeArea sizeArea,
    PlantTypeId plantTypeId,
    String location,
    OrganizationId organizationId
) {}

