package com.floweytech.agrotrack.platform.organization.domain.model.commands;

public record CreateOrganizationCommand(
    Long organizationId,
    String organizationName,
    Integer maxPlots,
    Long ownerProfileId,
    Long subscriptionId
) {
}
