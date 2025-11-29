package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.Task;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllTasksQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetTaskByIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetTasksByAssigneeProfileIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetTasksByAssignedToProfileIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetTasksByOrganizationIdQuery;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {
    List<Task> handle(GetAllTasksQuery query);
    Optional<Task> handle(GetTaskByIdQuery query);
    List<Task> handle(GetTasksByAssigneeProfileIdQuery query);
    List<Task> handle(GetTasksByAssignedToProfileIdQuery query);
    List<Task> handle(GetTasksByOrganizationIdQuery query);
}
