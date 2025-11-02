package com.floweytech.agrotrack.platform.organization.interfaces.rest;

import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.OrganizationId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.SubscriptionId;
import com.floweytech.agrotrack.platform.organization.domain.services.OrganizationCommandService;
import com.floweytech.agrotrack.platform.organization.domain.services.OrganizationQueryService;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.resources.AddProfileResource;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.resources.OrganizationResource;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.resources.RemoveProfileResource;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.resources.UpdateOrganizationNameResource;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.transform.AddProfileCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.transform.OrganizationResourceFromEntityAssembler;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.transform.RemoveProfileCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.organization.interfaces.rest.transform.UpdateOrganizationNameCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/organizations", produces = "application/json")
@Tag(name = "Organizations", description = "Organization Management Endpoints")
public class OrganizationController {

    private final OrganizationCommandService organizationCommandService;
    private final OrganizationQueryService organizationQueryService;

    public OrganizationController(OrganizationCommandService organizationCommandService,
                                   OrganizationQueryService organizationQueryService) {
        this.organizationCommandService = organizationCommandService;
        this.organizationQueryService = organizationQueryService;
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResource> getOrganizationById(@PathVariable Long organizationId) {
        var organization = organizationQueryService.getByOrganizationId(new OrganizationId(organizationId));

        return organization.map(org -> ResponseEntity.ok(OrganizationResourceFromEntityAssembler.toResourceFromEntity(org)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name/{organizationName}")
    public ResponseEntity<OrganizationResource> getOrganizationByName(@PathVariable String organizationName) {
        var organization = organizationQueryService.getByOrganizationName(organizationName);

        return organization.map(org -> ResponseEntity.ok(OrganizationResourceFromEntityAssembler.toResourceFromEntity(org)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-subscription/{subscriptionId}")
    public ResponseEntity<OrganizationResource> getOrganizationBySubscriptionId(@PathVariable Long subscriptionId) {
        var organization = organizationQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));

        return organization.map(org -> ResponseEntity.ok(OrganizationResourceFromEntityAssembler.toResourceFromEntity(org)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{organizationId}/name")
    public ResponseEntity<OrganizationResource> updateOrganizationName(
            @PathVariable Long organizationId,
            @Valid @RequestBody UpdateOrganizationNameResource resource) {

        var command = UpdateOrganizationNameCommandFromResourceAssembler.toCommandFromResource(organizationId, resource);
        organizationCommandService.handle(command);

        var organization = organizationQueryService.getByOrganizationId(new OrganizationId(organizationId));
        return organization.map(org -> ResponseEntity.ok(OrganizationResourceFromEntityAssembler.toResourceFromEntity(org)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{organizationId}/profiles/add")
    public ResponseEntity<OrganizationResource> addProfile(
            @PathVariable Long organizationId,
            @Valid @RequestBody AddProfileResource resource) {

        var command = AddProfileCommandFromResourceAssembler.toCommandFromResource(organizationId, resource);
        organizationCommandService.handle(command);

        var organization = organizationQueryService.getByOrganizationId(new OrganizationId(organizationId));
        return organization.map(org -> ResponseEntity.ok(OrganizationResourceFromEntityAssembler.toResourceFromEntity(org)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{organizationId}/profiles/remove")
    public ResponseEntity<OrganizationResource> removeProfile(
            @PathVariable Long organizationId,
            @Valid @RequestBody RemoveProfileResource resource) {

        var command = RemoveProfileCommandFromResourceAssembler.toCommandFromResource(organizationId, resource);
        organizationCommandService.handle(command);

        var organization = organizationQueryService.getByOrganizationId(new OrganizationId(organizationId));
        return organization.map(org -> ResponseEntity.ok(OrganizationResourceFromEntityAssembler.toResourceFromEntity(org)))
                .orElse(ResponseEntity.notFound().build());
    }
}

