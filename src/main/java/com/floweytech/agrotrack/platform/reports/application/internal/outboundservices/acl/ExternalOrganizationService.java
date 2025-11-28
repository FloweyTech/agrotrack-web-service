package com.floweytech.agrotrack.platform.reports.application.internal.outboundservices.acl;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlotId;
import com.floweytech.agrotrack.platform.organization.interfaces.acl.OrganizationContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalOrganizationService {

    private final OrganizationContextFacade organizationContextFacade;

    public ExternalOrganizationService(OrganizationContextFacade organizationContextFacade) {
        this.organizationContextFacade = organizationContextFacade;
    }

    /**
     * Verifica si un Plot existe en el contexto externo
     */
    public boolean fetchPlotExists(PlotId plotId) {
        return organizationContextFacade.existsPlotById(plotId.value());
    }
}
