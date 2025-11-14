package com.floweytech.agrotrack.platform.suscription.domain.service;

import com.floweytech.agrotrack.platform.suscription.domain.model.commands.ActivateSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CancelSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CreateSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.ExpireSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {
    Long handle(CreateSubscriptionCommand command);
    Optional<Long> handle(ActivateSubscriptionCommand command);
    Optional<Long> handle(CancelSubscriptionCommand command);
    Optional<Long> handle(ExpireSubscriptionCommand command);
}
