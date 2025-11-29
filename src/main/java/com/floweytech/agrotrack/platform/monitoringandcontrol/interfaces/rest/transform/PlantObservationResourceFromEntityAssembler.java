package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.entities.PlantObservation;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.PlantObservationResource;

public class PlantObservationResourceFromEntityAssembler {

    /**
     * Converts PlantObservation entity to PlantObservationResource.
     */
    public static PlantObservationResource toResourceFromEntity(PlantObservation entity) {
        var data = entity.getPlantObservationData();

        return new PlantObservationResource(
                entity.getId(),
                data.heightCm(),
                data.leafCount(),
                data.fruitCount(),
                data.notes()
        );
    }
}
