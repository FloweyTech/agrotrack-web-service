package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.DateRange;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.MaterialUsed;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskDetails;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskStatus;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.ProfileId;

import java.util.List;

/**
 * Command to modify an existing task with updated details, date range, status, and materials.
 * Validates that all required value objects are provided.
 */
public record ModifyTaskCommand (
    Long taskId,
    ProfileId modifyTaskForProfileId,
    TaskDetails taskDetails,
    DateRange dateRange,
    TaskStatus taskStatus,
    List<MaterialUsed> materialsUsed
){
    public  ModifyTaskCommand{
        if (taskId == null)
            throw new IllegalArgumentException("taskId cannot be null");
        if (modifyTaskForProfileId == null)
            throw new IllegalArgumentException("modifyTaskForProfileId cannot be null");
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
