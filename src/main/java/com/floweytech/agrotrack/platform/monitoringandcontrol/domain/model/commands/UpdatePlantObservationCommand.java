package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlantObservationData;

public record UpdatePlantObservationCommand(
        Long observationId,
        PlantObservationData observationData
) {
    public UpdatePlantObservationCommand {
        if (observationId == null)
            throw new IllegalArgumentException("observationId cannot be null");
        if (observationData == null)
            throw new IllegalArgumentException("observationData cannot be null");
    }
}
