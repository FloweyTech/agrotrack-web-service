package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.AddPlantObservationCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlantObservationData;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.AddPlantObservationResource;

public class AddPlantObservationCommandFromResourceAssembler {

    /**
     * Converts AddPlantObservationResource to AddPlantObservationCommand.
     */
    public static AddPlantObservationCommand toCommandFromResource(AddPlantObservationResource resource) {

        var data = new PlantObservationData(
                resource.heightCm(),
                resource.leafCount(),
                resource.fruitCount(),
                resource.notes()
        );

        return new AddPlantObservationCommand(data);
    }
}
