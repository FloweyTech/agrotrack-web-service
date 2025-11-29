package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.UpdateTaskStatusCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskStatus;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.UpdateTaskStatusResource;

public class UpdateTaskStatusCommandFromResourceAssembler {
    public static UpdateTaskStatusCommand toCommandFromResource(Long taskId, UpdateTaskStatusResource resource) {
        TaskStatus taskStatus;
        try {
            taskStatus = TaskStatus.valueOf(resource.status().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task status: " + resource.status() +
                    ". Valid values are: PENDING, IN_PROGRESS, COMPLETED, CANCELLED");
        }

        return new UpdateTaskStatusCommand(taskId, taskStatus);
    }
}

