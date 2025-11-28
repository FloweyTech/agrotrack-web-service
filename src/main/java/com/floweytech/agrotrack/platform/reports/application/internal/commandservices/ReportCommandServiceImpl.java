package com.floweytech.agrotrack.platform.reports.application.internal.commandservices;


import com.floweytech.agrotrack.platform.reports.application.internal.outboundservices.acl.ExternalOrganizationService;
import com.floweytech.agrotrack.platform.reports.domain.model.aggregates.Report;
import com.floweytech.agrotrack.platform.reports.domain.model.commands.CreateReportCommand;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportPeriod;
import com.floweytech.agrotrack.platform.reports.domain.services.ReportCommandService;
import com.floweytech.agrotrack.platform.reports.infrastructure.persistence.jpa.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;
    private final ExternalOrganizationService externalOrganizationService;

    public ReportCommandServiceImpl(
            ReportRepository reportRepository,
            ExternalOrganizationService externalOrganizationService) {
        this.reportRepository = reportRepository;
        this.externalOrganizationService = externalOrganizationService;
    }

    @Override
    public Long handle(CreateReportCommand command ) {

        var plotExists = externalOrganizationService.fetchPlotExists(command.plotId());
        if (!plotExists) {
            throw new IllegalArgumentException("Plot with ID " + command.plotId().value() + " not found.");
        }

        var period = new ReportPeriod(command.periodStart(), command.periodEnd());

        var reportExists = reportRepository.existsByPlotIdAndTypeAndReportPeriod(
                command.plotId(),
                command.type(),
                period
        );

        if (reportExists) {
            throw new IllegalArgumentException("A report of type " + command.type() + " already exists for this plot in the selected period.");
        }

        var report = new Report(command);
        try {
            reportRepository.save(report);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving report");
        }
        return report.getId();
    }

}
