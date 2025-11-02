package com.floweytech.agrotrack.platform.suscription.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.suscription.domain.model.aggregates.Subscription;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {

    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(
            entity.getId(),
            entity.getSubscriptionId().value(),
            entity.getSubscriptionPlan().name(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getSubscriptionStatus().name(),
            entity.getPrice().amount(),
            entity.getPrice().currency().getCurrencyCode()
        );
    }
}

