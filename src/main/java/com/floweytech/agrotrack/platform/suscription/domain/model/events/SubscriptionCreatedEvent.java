package com.floweytech.agrotrack.platform.suscription.domain.model.events;

public record SubscriptionCreatedEvent(
    Long subscriptionId,
    String organizationName,
    Integer maxPlots,
    Long ownerProfileId
) {
}
