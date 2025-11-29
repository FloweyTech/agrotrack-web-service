package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources;

public record AddPlantObservationResource(
        Double heightCm,
        Integer leafCount,
        Integer fruitCount,
        String notes
) {}
