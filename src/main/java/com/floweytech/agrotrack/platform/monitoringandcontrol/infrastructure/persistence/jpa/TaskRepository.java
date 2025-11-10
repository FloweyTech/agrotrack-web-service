package com.floweytech.agrotrack.platform.monitoringandcontrol.infrastructure.persistence.jpa;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.Task;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Find tasks by assigned profile ID
     * @param profileId
     * @return List of tasks
     */
    List<Task> findByAssignTaskToProfileId_ProfileId(Long profileId);

    /**
     * Find tasks by status
     * @param status
     * @return List of tasks
     */
    List<Task> findByTaskStatus(TaskStatus status);

}
