package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreateTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.ModifyTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.*;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.CreateTaskResource;

import java.util.List;
import java.util.stream.Collectors;

public class CreateTaskCommandFromResourceAssembler {
    /**
     * Converts a CreateTaskResource to a CreateTaskCommand.
     * @param resource CreateTaskResource to convert
     * @return CreateTaskCommand created from the resource
     */
    public static CreateTaskCommand toCommandFromResource(CreateTaskResource resource) {
        var assignedTaskToProfileId = new ProfileId(resource.assignTaskToProfileId());
        var taskDetails = new TaskDetails(resource.title(), resource.description());
        var dateRange = new DateRange(resource.startDate(), resource.endDate());

        // Simple conversion: each string becomes a MaterialUsed with default quantity and unit
        List<MaterialUsed> materialsUsed = resource.materialsUsed().stream()
                .map(m -> new MaterialUsed(m.materialName(), m.quantity(), m.unit()))
                .collect(Collectors.toList());


        return new CreateTaskCommand(
                assignedTaskToProfileId,
                taskDetails,
                dateRange,
                resource.taskStatus(),
                materialsUsed
        );
    }

    /**
     * Converts a CreateTaskResource and taskId to a ModifyTaskCommand.
     * @param taskId ID of the task to modify
     * @param resource CreateTaskResource with the updated data
     * @return ModifyTaskCommand created from the taskId and resource
     */
    public static ModifyTaskCommand toModifyCommandFromResource(Long taskId, CreateTaskResource resource) {
        var modifyTaskForProfileId = new ProfileId(resource.assignTaskToProfileId());
        var taskDetails = new TaskDetails(resource.title(), resource.description());
        var dateRange = new DateRange(resource.startDate(), resource.endDate());

        // Convert MaterialUsedResource list to MaterialUsed list
        List<MaterialUsed> materialsUsed = resource.materialsUsed().stream()
                .map(m -> new MaterialUsed(m.materialName(), m.quantity(), m.unit()))
                .collect(Collectors.toList());

        return new ModifyTaskCommand(
                taskId,
                modifyTaskForProfileId,
                taskDetails,
                dateRange,
                resource.taskStatus(),
                materialsUsed
        );
    }
}
