package com.floweytech.agrotrack.platform.iam.domain.model.commands;

public record SignInCommand(
        String identifier,
        String password
) {
}
