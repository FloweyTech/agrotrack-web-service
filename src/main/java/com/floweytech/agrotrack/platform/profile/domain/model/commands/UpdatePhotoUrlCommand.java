package com.floweytech.agrotrack.platform.profile.domain.model.commands;

public record UpdatePhotoUrlCommand(
    Long profileId,
    String photoUrl
) {
}
