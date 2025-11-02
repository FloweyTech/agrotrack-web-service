package com.floweytech.agrotrack.platform.organization.domain.model.commands;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;

public record ReassignPlantTypeCommand(
    Long plotId,
    PlantTypeId plantTypeId
) {
}

