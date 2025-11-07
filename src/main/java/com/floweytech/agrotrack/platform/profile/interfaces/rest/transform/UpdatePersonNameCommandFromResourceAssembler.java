package com.floweytech.agrotrack.platform.profile.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.profile.domain.model.commands.UpdatePersonNameCommand;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.resources.UpdatePersonNameResource;

public class UpdatePersonNameCommandFromResourceAssembler {
    public static UpdatePersonNameCommand toCommandFromResource(Long profileId, UpdatePersonNameResource resource) {
        return new UpdatePersonNameCommand(
                profileId,
                resource.firstName(),
                resource.lastName()
        );
    }
}

