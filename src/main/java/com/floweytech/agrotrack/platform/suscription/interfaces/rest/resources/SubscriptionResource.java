package com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.Date;

public record SubscriptionResource(
    Long id,
    Long subscriptionId,
    String subscriptionPlan,
    Date startDate,
    Date endDate,
    String subscriptionStatus,
    BigDecimal price,
    String currency
) {
}

