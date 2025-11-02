package com.floweytech.agrotrack.platform.suscription.domain.model.events;

public record SubscriptionCreatedEvent(
    Long subscriptionId,
    Long organizationId,
    String organizationName,
    Integer maxPlots,
    Long ownerProfileId
) {
}

