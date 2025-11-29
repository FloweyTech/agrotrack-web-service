package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.events;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.PlantSamplingSession;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlantObservationRemovedEvent extends ApplicationEvent {

    private final Long sessionId;
    private final Long observationId;

    public PlantObservationRemovedEvent(PlantSamplingSession source, Long sessionId, Long observationId) {
        super(source);
        this.sessionId = sessionId;
        this.observationId = observationId;
    }

    public Long getSessionId() { return sessionId; }
    public Long getObservationId() { return observationId; }
}
