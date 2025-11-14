package com.floweytech.agrotrack.platform.reports.domain.services;

import com.floweytech.agrotrack.platform.reports.domain.model.commands.CreateReportCommand;

/**
 * ReportCommandService
 * Service that handles report commands
 */
public interface ReportCommandService {

    /**
     * Handle a create report command
     * @param command
     * @return
     */
    Long handle (CreateReportCommand command);
}
