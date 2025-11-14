package com.floweytech.agrotrack.platform.reports.domain.services;

import com.floweytech.agrotrack.platform.reports.domain.model.aggregates.Report;
import com.floweytech.agrotrack.platform.reports.domain.model.queries.GetReportByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ReportQueryService
 */
public interface ReportQueryService {
    /**
     * Handle a get report by id query
     * @param query The get report by id query containing the report id
     * @return The report
     * @see GetReportByIdQuery
     *
     */
    Optional<Report> handle(GetReportByIdQuery query);

}
