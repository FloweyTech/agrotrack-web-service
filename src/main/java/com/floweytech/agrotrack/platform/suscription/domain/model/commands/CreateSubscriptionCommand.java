package com.floweytech.agrotrack.platform.suscription.domain.model.commands;

import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionId;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionPlan;

import java.util.Date;

public record CreateSubscriptionCommand(
    SubscriptionId subscriptionId,
    SubscriptionPlan subscriptionPlan,
    Date startDate,
    Date endDate,
    Long organizationId,
    String organizationName,
    Long ownerProfileId
) {
}
