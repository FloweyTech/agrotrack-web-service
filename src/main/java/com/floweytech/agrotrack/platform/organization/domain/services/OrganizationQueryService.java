package com.floweytech.agrotrack.platform.organization.domain.services;

import com.floweytech.agrotrack.platform.organization.domain.model.aggregate.Organization;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SubscriptionId;

import java.util.Optional;

public interface OrganizationQueryService {
    Optional<Organization> getByOrganizationId(OrganizationId organizationId);
    Optional<Organization> getByOrganizationName(String organizationName);
    Optional<Organization> getBySubscriptionId(SubscriptionId subscriptionId);
}
