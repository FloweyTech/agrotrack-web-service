package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources;

public record SampleAverageResource(
        Double avgHeightCm,
        Double avgLeafCount,
        Double avgFruitCount
) {}
