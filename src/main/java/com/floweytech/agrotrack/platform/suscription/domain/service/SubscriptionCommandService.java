package com.floweytech.agrotrack.platform.suscription.domain.service;

import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CreateSubscriptionCommand;

public interface SubscriptionCommandService {
    void handle(CreateSubscriptionCommand command);
}
