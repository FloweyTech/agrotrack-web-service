package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.EnvironmentReading;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreateEnvironmentReadingCommand;

import java.util.Optional;

/**
 * Environment Reading Command Service Interface
 */
public interface EnvironmentReadingCommandService {
    /**
     * Handle Create Environment Reading Command
     * @param command The {@link CreateEnvironmentReadingCommand} Command
     * @return A {@link EnvironmentReading} instance if the command is valid, otherwise empty
     * @throws IllegalArgumentException if the command is invalid
     */

    Optional<EnvironmentReading> handle(CreateEnvironmentReadingCommand command);
}
