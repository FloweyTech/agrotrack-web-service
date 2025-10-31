package com.floweytech.agrotrack.platform.organization.domain.model.aggregate;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SizeArea;
import com.floweytech.agrotrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Plot extends AuditableAbstractAggregateRoot<Plot> {
    @Embedded
    private PlotId plotId;
    private String plotName;
    @Embedded
    private SizeArea sizeArea;
    @Embedded
    private PlantTypeId plantTypeId;
    private String location;
    // TODO: agregar referencia a organization


}
