package com.floweytech.agrotrack.platform.reports.application.internal.eventhandlers;

import com.floweytech.agrotrack.platform.reports.domain.model.events.ReportCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Event handler for a Report Created
 *
 * @author Diego Vilca
 */
@Service
public class ReportCreatedEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportCreatedEventHandler.class);

    @EventListener
    public void on(ReportCreatedEvent event) {
        LOGGER.info("Domain Event received: Report with ID {} created for Plot {} in Organization {}",
                event.getId(),
                event.getPlotId().value(),
                event.getOrganizationId().value());

    }
}
