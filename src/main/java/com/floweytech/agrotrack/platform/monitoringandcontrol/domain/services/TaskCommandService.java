package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.Task;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreateTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.DeleteTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.ModifyTaskCommand;

import java.util.Optional;

public interface TaskCommandService {
    /**
     * Handles the creation of a new task.
     */
    Optional<Task> handle(CreateTaskCommand command);
    /**
     * Handles the deletion of an existing task.
     */
    Optional<Task> handle(DeleteTaskCommand command);
    /**
     * Handles the modification of an existing task.
     */
    Optional<Task> handle(ModifyTaskCommand command);
}
