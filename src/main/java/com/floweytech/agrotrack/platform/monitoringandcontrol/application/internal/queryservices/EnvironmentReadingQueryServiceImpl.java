package com.floweytech.agrotrack.platform.monitoringandcontrol.application.internal.queryservices;


import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.EnvironmentReading;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllEnvironmentReadingsByPlotIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllEnvironmentReadingsQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetEnvironmentReadingByIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.EnvironmentReadingQueryService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.infrastructure.persistence.jpa.EnvironmentReadingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Environment Reading Query Service Implementation
 */
@Service
public class EnvironmentReadingQueryServiceImpl implements EnvironmentReadingQueryService {
    private final EnvironmentReadingRepository environmentReadingRepository;

    /**
     * Constructor
     * @param environmentReadingRepository The {@link EnvironmentReadingRepository} instance
     */
    public EnvironmentReadingQueryServiceImpl(EnvironmentReadingRepository environmentReadingRepository){
        this.environmentReadingRepository = environmentReadingRepository;
    }

    @Override
    public Optional<EnvironmentReading> handle(GetEnvironmentReadingByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<EnvironmentReading> handle(GetAllEnvironmentReadingsQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<EnvironmentReading> handle(GetAllEnvironmentReadingsByPlotIdQuery query) {
        return Optional.empty();
    }
}
