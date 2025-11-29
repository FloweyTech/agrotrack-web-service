package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries;

public record GetTasksByAssigneeProfileIdQuery(Long assigneeProfileId) {
    public GetTasksByAssigneeProfileIdQuery {
        if (assigneeProfileId == null || assigneeProfileId <= 0)
            throw new IllegalArgumentException("assigneeProfileId cannot be null or less than or equal to zero");
    }
}

