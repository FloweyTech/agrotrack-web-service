package com.floweytech.agrotrack.platform.iam.interfaces.rest.transform;


import com.floweytech.agrotrack.platform.iam.domain.model.aggregates.User;
import com.floweytech.agrotrack.platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user){
        return new UserResource(user.getId(), user.getUsername(), user.getRole());
    }
}
