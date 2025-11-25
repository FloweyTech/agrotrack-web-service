package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources;

   import java.time.LocalDate;
import java.util.List;

public record TaskResource(
        Long assignTaskToProfileId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String taskStatus,
        List<MaterialUsedResource> materialsUsed
) {
}
