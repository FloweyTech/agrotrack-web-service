package com.floweytech.agrotrack.platform.monitoringandcontrol.application.internal.commandservices;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.EnvironmentReading;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreateEnvironmentReadingCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.EnvironmentReadingCommandService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.infrastructure.persistence.jpa.EnvironmentReadingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * EnvironmentReading Command Service Implementation
 */
@Service
public class EnvironmentReadingCommandServicelmlp implements EnvironmentReadingCommandService {
    private final EnvironmentReadingRepository environmentRepository;

    /**
     * Constructor
     * @param environmentRepository The {@link EnvironmentReadingRepository } instance
     */
    public EnvironmentReadingCommandServicelmlp(EnvironmentReadingRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    @Override
    public Optional<EnvironmentReading> handle(CreateEnvironmentReadingCommand command) {
        var environmentReading = new EnvironmentReading(command);
        environmentRepository.save(environmentReading);
        return Optional.of(environmentReading);
    }
}
