package com.floweytech.agrotrack.platform.organization.domain.model.commands;

public record RemoveProfileFromOrganizationCommand(
    Long organizationId,
    Long profileId
) {
}

