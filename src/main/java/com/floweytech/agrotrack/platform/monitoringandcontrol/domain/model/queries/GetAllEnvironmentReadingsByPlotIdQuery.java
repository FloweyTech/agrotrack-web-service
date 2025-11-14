package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;

public record GetAllEnvironmentReadingsByPlotIdQuery(PlotId plotId) {
}
