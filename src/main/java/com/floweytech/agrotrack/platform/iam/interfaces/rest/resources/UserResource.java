package com.floweytech.agrotrack.platform.iam.interfaces.rest.resources;

import com.floweytech.agrotrack.platform.iam.domain.model.valueobjects.Roles;

public record UserResource(
        Long id,
        String username,
        Roles role
        ) {
}
