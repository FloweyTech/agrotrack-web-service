package com.floweytech.agrotrack.platform.organization.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateOrganizationNameResource(
        @NotBlank(message = "Organization name is required")
        String organizationName
) {
}