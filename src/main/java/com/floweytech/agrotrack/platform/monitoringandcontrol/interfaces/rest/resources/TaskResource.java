package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskStatus;

import java.time.LocalDate;
import java.util.List;

public record TaskResource(
        Long assignTaskToProfileId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        TaskStatus taskStatus,
        List<com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.MaterialUsed> materialsUsed
) {
}
