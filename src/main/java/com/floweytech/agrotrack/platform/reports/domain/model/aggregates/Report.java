package com.floweytech.agrotrack.platform.reports.domain.model.aggregates;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlotId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.ProfileId;
import com.floweytech.agrotrack.platform.reports.domain.model.commands.CreateReportCommand;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportStatus;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportType;
import com.floweytech.agrotrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Plot aggregate root entity
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

    private LocalDate periodStart;

    private LocalDate periodEnd;

    private LocalDate generatedAt;

    /**
     * Default constructor
     */
    protected Report() {}


    public Report( CreateReportCommand command) {
        //this.profileId =
        this.status = command.status();
        this.plotId =command.plotId();
        this.organizationId = command.organizationId();
        this.type = command.type();
        this.periodStart = command.periodStart();
        this.periodEnd = command.periodEnd();
        this.generatedAt = command.generatedAt();

    }

}
