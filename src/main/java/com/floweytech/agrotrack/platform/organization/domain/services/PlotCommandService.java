package com.floweytech.agrotrack.platform.organization.domain.services;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlotCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.ReassignPlantTypeCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.ReassignSizeAreaCommand;

public interface PlotCommandService {
    void handle(CreatePlotCommand command);
    void handle(ReassignPlantTypeCommand command);
    void handle(ReassignSizeAreaCommand command);
}

