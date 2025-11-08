package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.DateRange;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.MaterialUsed;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskDetails;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskStatus;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.ProfileId;

import java.util.List;

public record CreateTaskCommand (
    ProfileId assignTaskToProfileId,
    TaskDetails taskDetails,
    DateRange dateRange,
    TaskStatus taskStatus,
    List<MaterialUsed> materialsUsed
) {
    /**
     * Validates the command.
     * @throws IllegalArgumentException if any of the required fields are null or invalid.
     */
    public CreateTaskCommand {
        if (assignTaskToProfileId == null)
            throw new IllegalArgumentException("assignTaskToProfileId cannot be null");
        if (taskDetails == null)
            throw new IllegalArgumentException("taskDetails cannot be null");
        if (dateRange == null)
            throw new IllegalArgumentException("dateRange cannot be null");
        if (taskStatus == null)
            throw new IllegalArgumentException("taskStatus cannot be null");
        if (materialsUsed == null)
            throw new IllegalArgumentException("materialsUsed cannot be null");
    }
}
