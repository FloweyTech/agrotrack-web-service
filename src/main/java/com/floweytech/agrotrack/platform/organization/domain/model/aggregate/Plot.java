package com.floweytech.agrotrack.platform.organization.domain.model.aggregate;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlotCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SizeArea;
import com.floweytech.agrotrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Plot extends AuditableAbstractAggregateRoot<Plot> {
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "plot_id"))
    private PlotId plotId;
    @Setter
    private String plotName;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "size", column = @Column(name = "size")),
        @AttributeOverride(name = "unit", column = @Column(name = "unit"))
    })
    private SizeArea sizeArea;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "plant_type_id"))
    private PlantTypeId plantTypeId;
    private String location;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "organization_id"))
    private OrganizationId organizationId;

    protected Plot() {
    }

    public Plot(CreatePlotCommand command) {
        this.plotId = command.plotId();
        this.plotName = command.plotName();
        this.sizeArea = command.sizeArea();
        this.plantTypeId = command.plantTypeId();
        this.location = command.location();
        this.organizationId = command.organizationId();
    }

    public void reassignPlantType(PlantTypeId newPlantTypeId) {
        this.plantTypeId = newPlantTypeId;
    }

    public void reassignSizeArea(SizeArea newSizeArea) {
        this.sizeArea = newSizeArea;
    }
}
