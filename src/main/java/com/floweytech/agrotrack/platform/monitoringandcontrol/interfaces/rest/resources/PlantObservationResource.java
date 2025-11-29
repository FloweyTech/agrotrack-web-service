package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources;

public record PlantObservationResource(
        Long id,
        Double heightCm,
        Integer leafCount,
        Integer fruitCount,
        String notes
) {}
