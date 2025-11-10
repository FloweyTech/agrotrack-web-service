package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.Task;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.MaterialUsedResource;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.TaskResource;

import java.util.stream.Collectors;

public class TaskResourceFromEntityAssembler {

    /**
     * Converts a Task entity to a TaskResource.
     * @param entity Task entity to convert
     * @return TaskResource created from the entity
     */
    public static TaskResource toResourceFromEntity (Task entity) {
        // Convert MaterialUsed (domain) to MaterialUsedResource (REST)
        var materialsUsedResources = entity.getMaterialsUsed().stream()
                .map(material -> new MaterialUsedResource(
                        material.getMaterialName(),
                        material.getQuantity(),
                        material.getUnit()
                ))
                .collect(Collectors.toList());

        return new TaskResource(
          entity.getAssignTaskToProfileId().profileId(),
          entity.getTaskDetails().getTaskTitle(),
          entity.getTaskDetails().getTaskDescription(),
          entity.getDateRange().getStartDate(),
          entity.getDateRange().getEndDate(),
          entity.getTaskStatus(),
          materialsUsedResources
        );
    }
}
