package com.floweytech.agrotrack.platform.suscription.appliaction.internal.queryservice;

import com.floweytech.agrotrack.platform.suscription.domain.model.aggregates.Subscription;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionId;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionStatus;
import com.floweytech.agrotrack.platform.suscription.domain.service.SubscriptionQueryService;
import com.floweytech.agrotrack.platform.suscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService{

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> getBySubscriptionId(SubscriptionId subscriptionId) {
        return subscriptionRepository.findBySubscriptionId(subscriptionId);
    }

    @Override
    public List<Subscription> getActiveSubscriptions() {
        return subscriptionRepository.findBySubscriptionStatus(SubscriptionStatus.ACTIVE);
    }
}