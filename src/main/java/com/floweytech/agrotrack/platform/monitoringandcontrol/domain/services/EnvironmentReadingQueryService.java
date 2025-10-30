package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.EnvironmentReading;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllEnvironmentReadingsByPlotIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllEnvironmentReadingsQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetEnvironmentReadingByIdQuery;

import java.util.Optional;

public interface EnvironmentReadingQueryService {

    Optional <EnvironmentReading> handle(GetEnvironmentReadingByIdQuery query);

    Optional <EnvironmentReading> handle(GetAllEnvironmentReadingsQuery query);

    Optional <EnvironmentReading> handle(GetAllEnvironmentReadingsByPlotIdQuery query);
}
