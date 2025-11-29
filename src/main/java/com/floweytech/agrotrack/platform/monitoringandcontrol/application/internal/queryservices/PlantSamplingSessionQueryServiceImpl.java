package com.floweytech.agrotrack.platform.monitoringandcontrol.application.internal.queryservices;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.PlantSamplingSession;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.entities.PlantObservation;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllPlantSamplingSessionsQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetObservationsBySessionIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetPlantSamplingSessionByIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetPlantSamplingSessionsByPlotIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.PlantSamplingSessionQueryService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.infrastructure.persistence.jpa.PlantSamplingSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantSamplingSessionQueryServiceImpl implements PlantSamplingSessionQueryService {

    private final PlantSamplingSessionRepository repository;

    public PlantSamplingSessionQueryServiceImpl(PlantSamplingSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PlantSamplingSession> handle(GetPlantSamplingSessionByIdQuery query) {
        return repository.findById(query.sessionId());
    }

    @Override
    public List<PlantSamplingSession> handle(GetAllPlantSamplingSessionsQuery query) {
        return repository.findAll();
    }

    @Override
    public List<PlantSamplingSession> handle(GetPlantSamplingSessionsByPlotIdQuery query) {
        return repository.findAllByPlotId(query.plotId());
    }

    @Override
    public List<PlantObservation> handle(GetObservationsBySessionIdQuery query) {
        return repository.findById(query.sessionId())
                .map(PlantSamplingSession::getObservations)
                .orElse(List.of());
    }
}
