package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries;

public record GetTasksByAssignedToProfileIdQuery(Long assignedToProfileId) {
    public GetTasksByAssignedToProfileIdQuery {
        if (assignedToProfileId == null || assignedToProfileId <= 0)
            throw new IllegalArgumentException("assignedToProfileId cannot be null or less than or equal to zero");
    }
}

