package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.PlantSamplingSession;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.entities.PlantObservation;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllPlantSamplingSessionsQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetObservationsBySessionIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetPlantSamplingSessionByIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetPlantSamplingSessionsByPlotIdQuery;

import java.util.List;
import java.util.Optional;

public interface PlantSamplingSessionQueryService {

    Optional<PlantSamplingSession> handle(GetPlantSamplingSessionByIdQuery query);

    List<PlantSamplingSession> handle(GetAllPlantSamplingSessionsQuery query);

    List<PlantSamplingSession> handle(GetPlantSamplingSessionsByPlotIdQuery query);

    List<PlantObservation> handle(GetObservationsBySessionIdQuery query);
}