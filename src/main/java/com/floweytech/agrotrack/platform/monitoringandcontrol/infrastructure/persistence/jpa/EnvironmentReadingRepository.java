package com.floweytech.agrotrack.platform.monitoringandcontrol.infrastructure.persistence.jpa;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.EnvironmentReading;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface EnvironmentReadingRepository extends JpaRepository<EnvironmentReading, Long> {

    /**
     * Find all EnvironmentReadings by PlotId
     * @param plotId The PlotId
     * @return List of EnvironmentReadings
     */
    List<EnvironmentReading> findAllByPlotId(PlotId plotId);

}
