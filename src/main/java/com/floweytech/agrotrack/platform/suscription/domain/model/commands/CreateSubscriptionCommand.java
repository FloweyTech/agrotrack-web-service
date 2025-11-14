package com.floweytech.agrotrack.platform.suscription.domain.model.commands;

import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionPlan;

import java.util.Date;

public record CreateSubscriptionCommand(
    SubscriptionPlan subscriptionPlan,
    Date startDate,
    Date endDate,
    String organizationName,
    Long ownerProfileId
) {
}
