package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.commands.CreatePlantSamplingSessionCommand;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.CreatePlantSamplingSessionResource;

public class CreatePlantSamplingSessionCommandFromResourceAssembler {

    /**
     * Converts CreatePlantSamplingSessionResource to CreatePlantSamplingSessionCommand.
     */
    public static CreatePlantSamplingSessionCommand toCommandFromResource(CreatePlantSamplingSessionResource resource) {

        var plotId = new PlotId(resource.plotId().plotId());

        return new CreatePlantSamplingSessionCommand(
                plotId,
                resource.sampledAt()
        );
    }
}
