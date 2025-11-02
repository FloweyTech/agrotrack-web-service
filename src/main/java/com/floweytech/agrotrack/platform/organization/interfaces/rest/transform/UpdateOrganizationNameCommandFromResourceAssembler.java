package com.floweytech.agrotrack.platform.organization.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.UpdateOrganizationNameCommand;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.resources.UpdateOrganizationNameResource;

public class UpdateOrganizationNameCommandFromResourceAssembler {

    public static UpdateOrganizationNameCommand toCommandFromResource(Long organizationId, UpdateOrganizationNameResource resource) {
        return new UpdateOrganizationNameCommand(
            organizationId,
            resource.organizationName()
        );
    }
}

