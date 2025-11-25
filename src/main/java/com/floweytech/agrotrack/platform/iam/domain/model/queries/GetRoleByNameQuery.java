package com.floweytech.agrotrack.platform.iam.domain.model.queries;

import com.floweytech.agrotrack.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
