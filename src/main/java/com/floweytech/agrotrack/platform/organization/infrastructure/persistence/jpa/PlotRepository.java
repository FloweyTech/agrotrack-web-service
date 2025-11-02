package com.floweytech.agrotrack.platform.organization.infrastructure.persistence.jpa;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.organization.domain.model.aggregate.Plot;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {
    Optional<Plot> findByPlotId(PlotId plotId);
    boolean existsByPlotId(PlotId plotId);
    List<Plot> findAllByOrganizationId(OrganizationId organizationId);
    List<Plot> findAllByPlotName(String plotName);
}
