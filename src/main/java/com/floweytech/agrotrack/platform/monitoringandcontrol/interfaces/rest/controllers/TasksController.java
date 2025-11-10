package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.controllers;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.DeleteTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetAllTasksQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.queries.GetTaskByIdQuery;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.TaskCommandService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.TaskQueryService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.CreateTaskResource;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.TaskResource;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform.CreateTaskCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/tasks", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Tasks Management Endpoints")
public class TasksController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TasksController(TaskCommandService taskCommandService,
                           TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    })
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource  resource){
        var createTaskCommand = CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource);
        var task = taskCommandService.handle(createTaskCommand);
        if (task.isEmpty())
            return ResponseEntity.badRequest().build();

        var createdTask = task.get();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(createdTask);
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Update an existing task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input data"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResource> updateTask(
            @PathVariable Long taskId,
            @RequestBody CreateTaskResource resource) {

        var modifyTaskCommand = CreateTaskCommandFromResourceAssembler
                .toModifyCommandFromResource(taskId, resource);

        var task = taskCommandService.handle(modifyTaskCommand);

        if (task.isEmpty())
            return ResponseEntity.notFound().build();

        var updatedTask = task.get();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(updatedTask);

        return ResponseEntity.ok(taskResource);
    }


    @GetMapping("/{taskId}")
    @Operation(summary = "Get task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long taskId){
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = taskQueryService.handle(getTaskByIdQuery);
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        var taskEntity = task.get();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(taskEntity);
        return ResponseEntity.ok(taskResource);
    }

    @GetMapping
    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No tasks found")
    })
    public ResponseEntity<List<TaskResource>> getAllTasks(){
        var tasks = taskQueryService.handle(new GetAllTasksQuery());
        if (tasks.isEmpty()) return ResponseEntity.notFound().build();
        var taskResources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(taskResources);
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Delete a task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
        var deleteTaskCommand = new DeleteTaskCommand(taskId);
        taskCommandService.handle(deleteTaskCommand);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
