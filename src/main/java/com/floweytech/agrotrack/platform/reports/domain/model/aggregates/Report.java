package com.floweytech.agrotrack.platform.reports.domain.model.aggregates;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlotId;
import com.floweytech.agrotrack.platform.profile.domain.model.valueobjects.ProfileId;
import com.floweytech.agrotrack.platform.reports.domain.model.commands.CreateReportCommand;
import com.floweytech.agrotrack.platform.reports.domain.model.events.ReportCreatedEvent;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportPeriod;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportStatus;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportType;
import com.floweytech.agrotrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Report aggregate root
 * @summary
 * This aggregate root, represents the report that will be generated for a plot in an organization.
 *
 * @author Diego Vilca
 */
@Getter
@Entity
public class Report extends AuditableAbstractAggregateRoot<Report> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column = @Column(
                    name="requested_by", nullable = true))})
    private ProfileId profileId;


    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Embedded
    @AttributeOverride(name = "value", column= @Column(name="plot_id", nullable = false))
    private PlotId plotId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name="organization_id", nullable = false))
    private OrganizationId organizationId;


    @Enumerated(EnumType.STRING)
    private ReportType type;


    @Embedded
    private ReportPeriod reportPeriod;

    private LocalDateTime generatedAt;

    /**
     * Default constructor
     */
    protected Report() {}


    public Report( CreateReportCommand command) {
        this.profileId = command.profileId();
        this.status = ReportStatus.CREATED;
        this.plotId =command.plotId();
        this.organizationId = command.organizationId();
        this.type = command.type();
        this.reportPeriod = new ReportPeriod(command.periodStart(), command.periodEnd());
        this.generatedAt = LocalDateTime.now();

    }

    @PostPersist
    public void onPostPersist() {
        this.registerEvent(new ReportCreatedEvent(
                this,
                this.getId(),
                this.plotId,
                this.organizationId,
                this.type
        ));
    }

}
