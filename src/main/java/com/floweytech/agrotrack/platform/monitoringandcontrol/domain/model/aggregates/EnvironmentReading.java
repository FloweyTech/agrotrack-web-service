
package com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreateEnvironmentReadingCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.ReadingType;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.ReadingValue;
import com.floweytech.agrotrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * EnvironmentReading aggregate root
 * @summary
 * The EnvironmentReading class is an aggregate root that represents an environmental reading taken from a specific plot.
 * It is responsible for the handling the CreateEnvironmentReadingCommand command.
 */
@Entity
public class EnvironmentReading extends AuditableAbstractAggregateRoot<EnvironmentReading> {

    @Embedded
    private PlotId plotId;

    @Embedded
    private ReadingValue readingValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    private ReadingType type;

    @Column(nullable = false)
    @Getter
    private LocalDateTime measuredAt;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected EnvironmentReading() {}

    /**
     * Creates a new EnvironmentReading aggregate based on the CreateEnvironmentReadingCommand.
     * @param command - the CreateEnvironmentReadingCommand command.
     */
    public EnvironmentReading(CreateEnvironmentReadingCommand command) {
        this.plotId = command.plotId();
        this.type = command.type();
        this.readingValue = command.readingValue();
        this.measuredAt = command.measuredAt();
    }




}