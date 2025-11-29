package com.floweytech.agrotrack.platform.organization.domain.services;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlantTypeCommand;
import jakarta.servlet.http.HttpServletRequest;

public interface PlantTypeCommandService {
    Long handle(CreatePlantTypeCommand command, HttpServletRequest request);
}
