package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.Task;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {

    /**
     * Converts a Task entity to a TaskResource.
     * @param entity Task entity to convert
     * @return TaskResource created from the entity
     */
    public static TaskResource toResourceFromEntity (Task entity) {
        return new TaskResource(
          entity.getAssignTaskToProfileId().profileId(),
          entity.getTaskDetails().getTaskTitle(),
          entity.getTaskDetails().getTaskDescription(),
          entity.getDateRange().getStartDate(),
          entity.getDateRange().getEndDate(),
          entity.getTaskStatus(),
          entity.getMaterialsUsed()
        );
    }
}
