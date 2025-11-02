package com.floweytech.agrotrack.platform.organization.domain.services;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.AddProfileToOrganizationCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.RemoveProfileFromOrganizationCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.UpdateOrganizationNameCommand;

public interface OrganizationCommandService {
    void handle(UpdateOrganizationNameCommand command);
    void handle(AddProfileToOrganizationCommand command);
    void handle(RemoveProfileFromOrganizationCommand command);
}
