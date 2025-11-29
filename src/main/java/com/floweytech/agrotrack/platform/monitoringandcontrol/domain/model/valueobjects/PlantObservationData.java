package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Input data required to create or update a PlantObservation.
 */
@Embeddable
public record PlantObservationData(
        Double heightCm,
        Integer leafCount,
        Integer fruitCount,
        String notes
) {
}
