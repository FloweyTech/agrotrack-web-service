package com.floweytech.agrotrack.platform.suscription.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CreateSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionPlan;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {

    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
            SubscriptionPlan.valueOf(resource.subscriptionPlan()),
            resource.startDate(),
            resource.endDate(),
            resource.organizationName(),
            resource.ownerProfileId()
        );
    }
}
