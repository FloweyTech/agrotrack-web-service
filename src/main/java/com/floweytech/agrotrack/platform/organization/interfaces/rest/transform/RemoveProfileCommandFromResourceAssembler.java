package com.floweytech.agrotrack.platform.organization.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.RemoveProfileFromOrganizationCommand;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.resources.RemoveProfileResource;

public class RemoveProfileCommandFromResourceAssembler {

    public static RemoveProfileFromOrganizationCommand toCommandFromResource(Long organizationId, RemoveProfileResource resource) {
        return new RemoveProfileFromOrganizationCommand(
            organizationId,
            resource.profileId()
        );
    }
}

