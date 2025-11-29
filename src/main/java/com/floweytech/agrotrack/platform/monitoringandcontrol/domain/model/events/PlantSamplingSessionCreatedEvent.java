package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.events;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.PlantSamplingSession;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlantSamplingSessionCreatedEvent extends ApplicationEvent {

    private final Long sessionId;

    public PlantSamplingSessionCreatedEvent(PlantSamplingSession source, Long sessionId) {
        super(source);
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return sessionId;
    }
}
