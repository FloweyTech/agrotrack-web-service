package com.floweytech.agrotrack.platform.iam.interfaces.rest.transform;


import com.floweytech.agrotrack.platform.iam.domain.model.commands.SignUpCommand;
import com.floweytech.agrotrack.platform.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource){
        return new SignUpCommand(
                resource.username(),
                resource.email(),
                resource.password(),
                resource.role(),
                resource.firstName(),
                resource.lastName(),
                resource.photoUrl()
        );
    }
}
