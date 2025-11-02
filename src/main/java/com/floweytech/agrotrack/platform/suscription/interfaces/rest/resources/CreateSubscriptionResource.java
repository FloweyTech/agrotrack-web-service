package com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record CreateSubscriptionResource(
    @NotNull(message = "Subscription ID is required")
    @Positive(message = "Subscription ID must be positive")
    Long subscriptionId,

    @NotNull(message = "Subscription plan is required")
    String subscriptionPlan,

    @NotNull(message = "Start date is required")
    Date startDate,

    @NotNull(message = "End date is required")
    Date endDate,

    @NotNull(message = "Organization ID is required")
    @Positive(message = "Organization ID must be positive")
    Long organizationId,

    @NotBlank(message = "Organization name is required")
    String organizationName,

    @NotNull(message = "Owner profile ID is required")
    @Positive(message = "Owner profile ID must be positive")
    Long ownerProfileId
) {
}
