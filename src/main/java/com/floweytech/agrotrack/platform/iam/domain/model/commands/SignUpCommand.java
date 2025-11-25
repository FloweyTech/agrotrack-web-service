package com.floweytech.agrotrack.platform.iam.domain.model.commands;
import com.floweytech.agrotrack.platform.iam.domain.model.valueobjects.Roles;

import java.util.List;

/**
 * Sign up command
 * <p>
 *     This class represents the command to sign up a user.
 * </p>
 * @param username the username of the user
 * @param password the password of the user
 * @param role the role of the user
 *
 */
public record SignUpCommand(
        String username,
        String email,
        String password,
        Roles role,
        String firstName,
        String lastName,
        String photoUrl
) {}