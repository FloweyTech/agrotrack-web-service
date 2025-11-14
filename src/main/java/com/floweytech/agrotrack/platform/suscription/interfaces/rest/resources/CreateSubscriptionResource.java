package com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateSubscriptionResource(
    @NotNull(message = "Subscription plan is required")
    String subscriptionPlan,

    @NotNull(message = "Start date is required")
    Date startDate,

    @NotNull(message = "End date is required")
    Date endDate,

    @NotBlank(message = "Organization name is required")
    String organizationName
) {
}
