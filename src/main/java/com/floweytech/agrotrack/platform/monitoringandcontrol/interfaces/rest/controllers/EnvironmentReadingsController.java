package com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.controllers;

import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.EnvironmentReadingCommandService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.domain.services.EnvironmentReadingQueryService;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.CreateEnvironmentReadingResource;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.resources.EnvironmentReadingResource;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform.CreateEnvironmentReadingCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.monitoringandcontrol.interfaces.rest.transform.EnvironmentReadingResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/environmental-readings", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Environmental Readings", description = "Available endpoints for managing environmental readings")
public class EnvironmentReadingsController {

    private final EnvironmentReadingCommandService environmentReadingCommandService;
    private final EnvironmentReadingQueryService environmentReadingQueryService;

    /**
     * Instantiates a new {@link EnvironmentReadingsController} instance.
     */
    public EnvironmentReadingsController(EnvironmentReadingCommandService environmentReadingCommandService,
                                         EnvironmentReadingQueryService environmentReadingQueryService) {
        this.environmentReadingCommandService = environmentReadingCommandService;
        this.environmentReadingQueryService = environmentReadingQueryService;
    }

    /**
     * Create a new environmental reading.
     * @param resource The {@link CreateEnvironmentReadingResource} instance.
     * @return A {@link EnvironmentReadingResource} for the created Environment Reading
     *         or a bad request response if the creation failed.
     */
    @PostMapping
    @Operation(summary = "Create a new environment reading")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Environment reading created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    })
    public ResponseEntity<EnvironmentReadingResource> createEnvironmentReading(
            @RequestBody CreateEnvironmentReadingResource resource) {

        var createEnvironmentReadingCommand =
                CreateEnvironmentReadingCommandFromResourceAssembler.toCommandFromResource(resource);

        var environmentReading = environmentReadingCommandService.handle(createEnvironmentReadingCommand);

        if (environmentReading.isEmpty())
            return ResponseEntity.badRequest().build();

        var createdEnvironmentReading = environmentReading.get();
        var environmentReadingResource =
                EnvironmentReadingResourceFromEntityAssembler.toResourceFromEntity(createdEnvironmentReading);

        return new ResponseEntity<>(environmentReadingResource, HttpStatus.CREATED);
    }
}
