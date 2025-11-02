package com.floweytech.agrotrack.platform.suscription.appliaction.internal.commandservice;

import com.floweytech.agrotrack.platform.suscription.domain.model.aggregates.Subscription;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CreateSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.service.SubscriptionCommandService;
import com.floweytech.agrotrack.platform.suscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void handle(CreateSubscriptionCommand command) {
        if (subscriptionRepository.existsBySubscriptionId(command.subscriptionId())) {
            throw new IllegalArgumentException("Subscription with id " + command.subscriptionId().value() + " already exists");
        }

        var subscription = new Subscription(command);
        subscriptionRepository.save(subscription);
    }
}
