package com.floweytech.agrotrack.platform.reports.application.internal.commandservices;


import com.floweytech.agrotrack.platform.reports.domain.model.aggregates.Report;
import com.floweytech.agrotrack.platform.reports.domain.model.commands.CreateReportCommand;
import com.floweytech.agrotrack.platform.reports.domain.services.ReportCommandService;
import com.floweytech.agrotrack.platform.reports.infrastructure.persistence.jpa.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Long handle(CreateReportCommand command ) {
        var report = new Report(command);
        try {
            reportRepository.save(report);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving report");
        }
        return report.getId();
    }

}
