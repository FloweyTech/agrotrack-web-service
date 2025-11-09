package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.events;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.DateRange;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.MaterialUsed;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskDetails;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskStatus;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.ProfileId;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


import java.util.List;

/**
 * TaskModifiedEvent
 * Event representing the modification of a task.
 */
@Getter
public class TaskModifiedEvent extends ApplicationEvent {
    private final Long taskId;
    private final ProfileId modifyTaskForProfileId;
    private final TaskDetails taskDetails;
    private final DateRange dateRange;
    private final TaskStatus taskStatus;
    private final List<MaterialUsed> materialsUsed;


    /**
     * Constructor for TaskModifiedEvent.
     * @param source
     * @param taskId
     * @param modifyTaskForProfileId
     * @param taskDetails
     * @param dateRange
     * @param taskStatus
     * @param materialsUsed
     */
    public TaskModifiedEvent(
            Object source,
            Long taskId,
            ProfileId modifyTaskForProfileId,
            TaskDetails taskDetails,
            DateRange dateRange,
            TaskStatus taskStatus,
            List<MaterialUsed> materialsUsed
    ) {
        super(source);
        this.taskId = taskId;
        this.modifyTaskForProfileId = modifyTaskForProfileId;
        this.taskDetails = taskDetails;
        this.dateRange = dateRange;
        this.taskStatus = taskStatus;
        this.materialsUsed = materialsUsed;
    }
}
