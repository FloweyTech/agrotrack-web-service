package com.floweytech.agrotrack.platform.organization.application.internal.eventhandlers;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreateOrganizationCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.aggregate.Organization;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SubscriptionId;
import com.floweytech.agrotrack.platform.organization.infrastructure.persistence.jpa.OrganizationRepository;
import com.floweytech.agrotrack.platform.suscription.domain.model.events.SubscriptionActivatedEvent;
import com.floweytech.agrotrack.platform.suscription.domain.model.events.SubscriptionExpiredEvent;
import com.floweytech.agrotrack.platform.suscription.domain.model.events.SubscriptionCancelledEvent;
import com.floweytech.agrotrack.platform.suscription.domain.model.events.SubscriptionCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionEventHandler {

    private final OrganizationRepository organizationRepository;

    public SubscriptionEventHandler(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @EventListener
    public void on(SubscriptionCreatedEvent event) {
        var command = new CreateOrganizationCommand(
            event.organizationId(),
            event.organizationName(),
            event.maxPlots(),
            event.ownerProfileId(),
            event.subscriptionId()
        );

        var organization = new Organization(command);
        organizationRepository.save(organization);
    }

    @EventListener
    public void on(SubscriptionActivatedEvent event) {
        var subscriptionId = new SubscriptionId(event.subscriptionId());
        organizationRepository.findBySubscriptionId(subscriptionId)
            .ifPresent(organization -> {
                organization.activate();
                organizationRepository.save(organization);
            });
    }

    @EventListener
    public void on(SubscriptionExpiredEvent event) {
        var subscriptionId = new SubscriptionId(event.subscriptionId());
        organizationRepository.findBySubscriptionId(subscriptionId)
            .ifPresent(organization -> {
                organization.deactivate();
                organizationRepository.save(organization);
            });
    }

    @EventListener
    public void on(SubscriptionCancelledEvent event) {
        var subscriptionId = new SubscriptionId(event.subscriptionId());
        organizationRepository.findBySubscriptionId(subscriptionId)
            .ifPresent(organization -> {
                organization.deactivate();
                organizationRepository.save(organization);
            });
    }
}
