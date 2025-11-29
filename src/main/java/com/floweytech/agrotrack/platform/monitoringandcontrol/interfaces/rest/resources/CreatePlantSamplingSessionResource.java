package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;

import java.time.LocalDateTime;

public record CreatePlantSamplingSessionResource(
        PlotId plotId,
        LocalDateTime sampledAt
) {}
