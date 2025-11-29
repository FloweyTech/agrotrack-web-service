package com.floweytech.agrotrack.platform.monitoringandcontrol.infrastructure.persistence.jpa;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.aggregates.PlantSamplingSession;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantSamplingSessionRepository extends JpaRepository<PlantSamplingSession,Long> {
    List<PlantSamplingSession> findAllByPlotId(PlotId plotId);
}
