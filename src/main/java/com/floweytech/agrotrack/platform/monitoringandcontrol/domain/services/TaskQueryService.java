package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.Task;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllTasksQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetTaskByIdQuery;

import java.util.Optional;

public interface TaskQueryService {
    Optional <Task> handle(GetAllTasksQuery query);
    Optional <Task> handle(GetTaskByIdQuery query);
}
