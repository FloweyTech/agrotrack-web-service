package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.AddPlantObservationCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreatePlantSamplingSessionCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.RemovePlantObservationCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.UpdatePlantObservationCommand;

public interface PlantSamplingSessionCommandService {

    Long handle(CreatePlantSamplingSessionCommand command);

    Long handle(Long sessionId, AddPlantObservationCommand command);

    void handle(Long sessionId, UpdatePlantObservationCommand command);

    void handle(Long sessionId, RemovePlantObservationCommand command);
}
