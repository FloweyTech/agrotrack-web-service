package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.EnvironmentReading;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllEnvironmentReadingsByPlotIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllEnvironmentReadingsQuery;

import java.util.Optional;

public interface EnvironmentReadingQueryService {

    Optional <EnvironmentReading> handle(GetAllEnvironmentReadingsQuery query);

    Optional <EnvironmentReading> handle(GetAllEnvironmentReadingsByPlotIdQuery query);
}
