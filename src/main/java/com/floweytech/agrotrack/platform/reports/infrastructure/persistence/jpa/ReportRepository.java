package com.floweytech.agrotrack.platform.reports.infrastructure.persistence.jpa;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlotId;
import com.floweytech.agrotrack.platform.reports.domain.model.aggregates.Report;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportPeriod;
import com.floweytech.agrotrack.platform.reports.domain.model.valueobjects.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Report repository
 * <p>
 *   This interface is used to interact with the database and perform CRUD and business
 *   command and query supporting operations on the Report entity
 * </p>
 */
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    boolean existsByPlotIdAndTypeAndReportPeriod(PlotId plotId, ReportType type, ReportPeriod reportPeriod);

}
