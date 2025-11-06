package com.floweytech.agrotrack.platform.suscription.domain.service;

import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CreateSubscriptionCommand;

public interface SubscriptionCommandService {
    Long handle(CreateSubscriptionCommand command);
}
