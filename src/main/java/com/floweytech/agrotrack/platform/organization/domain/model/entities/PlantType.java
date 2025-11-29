package com.floweytech.agrotrack.platform.organization.domain.model.entities;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlantTypeCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypes;
import com.floweytech.agrotrack.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class PlantType extends AuditableModel {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "plant_type_id", unique = true))
    private PlantTypeId plantTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "plant_type")
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
        this.predefined = false;
    }

    @PostPersist
    protected void onPostPersist() {
        this.plantTypeId = new PlantTypeId(this.getId());
    }

    public void markAsPredefined() {
        this.predefined = true;
    }
}
