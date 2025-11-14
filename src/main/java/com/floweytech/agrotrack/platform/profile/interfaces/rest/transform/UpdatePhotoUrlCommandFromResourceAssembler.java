package com.floweytech.agrotrack.platform.profile.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.profile.domain.model.commands.UpdatePhotoUrlCommand;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.resources.UpdatePhotoUrlResource;

public class UpdatePhotoUrlCommandFromResourceAssembler {
    public static UpdatePhotoUrlCommand toCommandFromResource(Long profileId, UpdatePhotoUrlResource resource) {
        return new UpdatePhotoUrlCommand(
                profileId,
                resource.photoUrl()
        );
    }
}

