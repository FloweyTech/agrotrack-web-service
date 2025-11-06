package com.floweytech.agrotrack.platform.iam.domain.model.events;

public record UserRegisteredEvent(
        Long userId,
        String firstName,
        String lastName,
        String photoUrl
) {
}

