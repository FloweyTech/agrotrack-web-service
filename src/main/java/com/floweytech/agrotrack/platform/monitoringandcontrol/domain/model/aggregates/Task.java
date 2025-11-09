package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates;


import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreateTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.ModifyTaskCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.events.TaskCreatedEvent;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.events.TaskDeletedEvent;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.events.TaskModifiedEvent;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.DateRange;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.MaterialUsed;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskDetails;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskStatus;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.ProfileId;
import com.floweytech.agrotrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Task extends AuditableAbstractAggregateRoot<Task> {

    @Embedded
    @Getter
    private ProfileId assignTaskToProfileId;

    @Embedded
    @Getter
    private TaskDetails taskDetails;

    @Embedded
    @Getter
    private DateRange dateRange;

    @Enumerated(EnumType.STRING)
    @Getter
    private TaskStatus taskStatus;

    @ElementCollection
    @CollectionTable(name = "task_materials_used", joinColumns = @JoinColumn(name = "task_id"))
    @Getter
    private List<MaterialUsed> materialsUsed;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Task() {}

    public Task(CreateTaskCommand command) {
        this.assignTaskToProfileId = command.assignTaskToProfileId();
        this.taskDetails = command.taskDetails();
        this.dateRange = command.dateRange();
        this.taskStatus = command.taskStatus();
        this.materialsUsed = command.materialsUsed();

        this.registerEvent(new TaskCreatedEvent(
                this,
                this.getId(),
                this.assignTaskToProfileId,
                this.taskDetails,
                this.dateRange,
                this.taskStatus,
                this.materialsUsed

        ));
    }

    public void applyTaskMofication(ModifyTaskCommand command){

        this.assignTaskToProfileId = command.modifyTaskForProfileId();
        this.taskDetails = command.taskDetails();
        this.dateRange = command.dateRange();
        this.taskStatus = command.taskStatus();
        this.materialsUsed = command.materialsUsed();

        this.registerEvent(new TaskModifiedEvent(
                this,
                this.getId(),
                this.assignTaskToProfileId,
                this.taskDetails,
                this.dateRange,
                this.taskStatus,
                this.materialsUsed
        ));
    }

    public void deleteTask() {
        this.registerEvent(new TaskDeletedEvent(
                this,
                this.getId()
        ));
    }

    /**
     * Sets the profile ID to whom the task is assigned.
     * @param assignTaskToProfileId
     */
    public void setAssignTaskToProfileId(ProfileId assignTaskToProfileId) {
        this.assignTaskToProfileId = assignTaskToProfileId;
    }

    /**
     * Updates the status of the task.
     * @param taskStatus
     */
    public void updateStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * Adds a material used to the task.
     * @param materialUsed
     */
    public void addMaterialUsed(MaterialUsed materialUsed) {
        this.materialsUsed.add(materialUsed);
    }


}
