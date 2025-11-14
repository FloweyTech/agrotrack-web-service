package com.floweytech.agrotrack.platform.profile.domain.model.commands;

public record UpdatePersonNameCommand(
    Long profileId,
    String firstName,
    String lastName
) {
}
