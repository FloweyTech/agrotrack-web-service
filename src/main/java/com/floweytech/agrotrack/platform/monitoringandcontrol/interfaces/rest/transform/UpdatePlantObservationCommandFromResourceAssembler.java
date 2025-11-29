package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.UpdatePlantObservationCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlantObservationData;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.UpdatePlantObservationResource;

public class UpdatePlantObservationCommandFromResourceAssembler {

    /**
     * Converts UpdatePlantObservationResource to UpdatePlantObservationCommand.
     */
    public static UpdatePlantObservationCommand toCommandFromResource(
            Long observationId,
            UpdatePlantObservationResource resource) {

        var data = new PlantObservationData(
                resource.heightCm(),
                resource.leafCount(),
                resource.fruitCount(),
                resource.notes()
        );

        return new UpdatePlantObservationCommand(
                observationId,
                data
        );
    }
}
