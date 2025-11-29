package com.floweytech.agrotrack.platform.organization.domain.model.commands;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypes;

public record CreatePlantTypeCommand(
        PlantTypeId plantTypeId,
        PlantTypes plantTypes,
        String name,
        String description
) {}