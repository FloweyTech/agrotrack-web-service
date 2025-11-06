package com.floweytech.agrotrack.platform.suscription.appliaction.internal.commandservice;

import com.floweytech.agrotrack.platform.suscription.domain.model.aggregates.Subscription;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CreateSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.events.SubscriptionCreatedEvent;
import com.floweytech.agrotrack.platform.suscription.domain.service.SubscriptionCommandService;
import com.floweytech.agrotrack.platform.suscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository,
                                          ApplicationEventPublisher eventPublisher) {
        this.subscriptionRepository = subscriptionRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Long handle(CreateSubscriptionCommand command) {
        var subscription = new Subscription(command);

        // Save and flush to generate the ID
        var savedSubscription = subscriptionRepository.saveAndFlush(subscription);

        // Now the subscriptionId is available (generated in @PostPersist)
        var event = new SubscriptionCreatedEvent(
            savedSubscription.getSubscriptionId().value(),
            command.organizationName(),
            savedSubscription.getMaxPlots(),
            command.ownerProfileId()
        );

        eventPublisher.publishEvent(event);

        return savedSubscription.getSubscriptionId().value();
    }
}
