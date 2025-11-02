package com.floweytech.agrotrack.platform.suscription.domain.service;

import com.floweytech.agrotrack.platform.suscription.domain.model.aggregates.Subscription;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionId;

import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    Optional<Subscription> getBySubscriptionId(SubscriptionId subscriptionId);
    List<Subscription> getActiveSubscriptions();
}
