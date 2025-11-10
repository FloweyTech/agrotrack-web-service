package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreateTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.DateRange;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.MaterialUsed;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.ProfileId;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskDetails;
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
                .map(materialName -> new MaterialUsed(materialName, 1.0, "unit"))
                .collect(Collectors.toList());

        return new CreateTaskCommand(
                assignedTaskToProfileId,
                taskDetails,
                dateRange,
                resource.taskStatus(),
                materialsUsed
        );
    }
}
