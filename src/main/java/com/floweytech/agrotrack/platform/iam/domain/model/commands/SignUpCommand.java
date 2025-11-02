package com.floweytech.agrotrack.platform.iam.domain.model.commands;
import com.floweytech.agrotrack.platform.iam.domain.model.valueobjects.Roles;

public record SignUpCommand(
   String username,
   String email,
   String password,
   Roles role
) {}
