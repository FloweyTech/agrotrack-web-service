package com.floweytech.agrotrack.platform.organization.application.acl;

import com.floweytech.agrotrack.platform.organization.infrastructure.persistence.jpa.repositories.PlotRepository;
import com.floweytech.agrotrack.platform.organization.interfaces.acl.OrganizationContextFacade;
import org.springframework.stereotype.Service;

@Service
public class OrganizationContextFacadeImpl implements OrganizationContextFacade {

    private final PlotRepository plotRepository;

    public OrganizationContextFacadeImpl(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    @Override
    public boolean existsPlotById(Long plotId) {
        return plotRepository.existsById(plotId);
    }
}