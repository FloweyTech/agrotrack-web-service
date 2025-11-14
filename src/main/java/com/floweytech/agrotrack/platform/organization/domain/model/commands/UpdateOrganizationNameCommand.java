package com.floweytech.agrotrack.platform.organization.domain.model.commands;

public record UpdateOrganizationNameCommand(
    Long organizationId,
    String organizationName
) {
}

