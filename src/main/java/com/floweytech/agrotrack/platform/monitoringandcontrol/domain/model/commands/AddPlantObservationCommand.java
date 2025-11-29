package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlantObservationData;

public record AddPlantObservationCommand(
        PlantObservationData observationData
) {
    public AddPlantObservationCommand {
        if (observationData == null)
            throw new IllegalArgumentException("observationData cannot be null");
    }
}
