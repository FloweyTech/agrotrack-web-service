package com.floweytech.agrotrack.platform.monitoringandcontrol.application.internal.queryservices;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.Task;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllTasksQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetTaskByIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.TaskQueryService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.infrastructure.persistence.jpa.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(GetAllTasksQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return Optional.empty();
    }
}
