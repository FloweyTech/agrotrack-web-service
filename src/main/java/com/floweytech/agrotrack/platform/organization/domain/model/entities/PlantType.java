package com.floweytech.agrotrack.platform.organization.domain.model.entities;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlantTypeCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypes;
import com.floweytech.agrotrack.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class PlantType extends AuditableModel {

    @Embedded
    private PlantTypeId plantTypeId;

    @Embedded
    private PlantTypes plantTypes;

    private String name;

    private String description;

    private Boolean predefined;

    protected PlantType() {
    }

    public PlantType(CreatePlantTypeCommand command) {
        this.plantTypeId = command.plantTypeId();
        this.plantTypes = command.plantTypes();
        this.name = command.name();
        this.description = command.description();
        this.predefined = command.predefined();

    }
}
