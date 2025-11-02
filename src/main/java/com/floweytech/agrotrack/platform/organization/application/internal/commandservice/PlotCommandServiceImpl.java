package com.floweytech.agrotrack.platform.organization.application.internal.commandservice;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.model.valueobjects.PlotId;
import com.floweytech.agrotrack.platform.organization.domain.model.aggregate.Plot;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlotCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.ReassignPlantTypeCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.commands.ReassignSizeAreaCommand;
import com.floweytech.agrotrack.platform.organization.domain.services.PlotCommandService;
import com.floweytech.agrotrack.platform.organization.infrastructure.persistence.jpa.PlotRepository;
import org.springframework.stereotype.Service;

@Service
public class PlotCommandServiceImpl implements PlotCommandService {

    private final PlotRepository plotRepository;

    public PlotCommandServiceImpl(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    @Override
    public void handle(CreatePlotCommand command) {
        if (plotRepository.existsByPlotId(command.plotId())) {
            throw new IllegalArgumentException("Plot with id " + command.plotId().plotId() + " already exists");
        }

        var plot = new Plot(command);
        plotRepository.save(plot);
    }

    @Override
    public void handle(ReassignPlantTypeCommand command) {
        var plotId = new PlotId(command.plotId());

        var plot = plotRepository.findByPlotId(plotId)
            .orElseThrow(() -> new IllegalArgumentException("Plot with id " + command.plotId() + " not found"));

        plot.reassignPlantType(command.plantTypeId());
        plotRepository.save(plot);
    }

    @Override
    public void handle(ReassignSizeAreaCommand command) {
        var plotId = new PlotId(command.plotId());

        var plot = plotRepository.findByPlotId(plotId)
            .orElseThrow(() -> new IllegalArgumentException("Plot with id " + command.plotId() + " not found"));

        plot.reassignSizeArea(command.sizeArea());
        plotRepository.save(plot);
    }
}
