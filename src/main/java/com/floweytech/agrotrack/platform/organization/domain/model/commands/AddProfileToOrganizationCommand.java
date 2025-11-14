package com.floweytech.agrotrack.platform.organization.domain.model.commands;

public record AddProfileToOrganizationCommand(
    Long organizationId,
    Long profileId
) {
}

