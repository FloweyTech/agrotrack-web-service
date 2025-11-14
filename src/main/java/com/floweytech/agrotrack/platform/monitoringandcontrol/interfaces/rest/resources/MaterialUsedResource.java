package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources;

public record MaterialUsedResource(
        String materialName,
        Double quantity,
        String unit
) {}
