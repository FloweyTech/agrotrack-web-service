package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PlotId (Long plotId) {
    public PlotId(){
        this(null);
    }

    public PlotId {
        if (plotId == null || plotId < 1)
            throw new IllegalArgumentException("plotId must be greater than zero");
    }
}

