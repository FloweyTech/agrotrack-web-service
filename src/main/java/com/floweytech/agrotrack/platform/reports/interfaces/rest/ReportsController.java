package com.floweytech.agrotrack.platform.reports.interfaces.rest;


import com.floweytech.agrotrack.platform.reports.domain.model.queries.GetReportByIdQuery;
import com.floweytech.agrotrack.platform.reports.domain.services.ReportCommandService;
import com.floweytech.agrotrack.platform.reports.domain.services.ReportQueryService;
import com.floweytech.agrotrack.platform.reports.interfaces.rest.resources.CreateReportResource;
import com.floweytech.agrotrack.platform.reports.interfaces.rest.resources.ReportResource;
import com.floweytech.agrotrack.platform.reports.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.reports.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.floweytech.agrotrack.platform.shared.interfaces.acl.TokenContextFacade;
import jakarta.servlet.http.HttpServletRequest;

/**
 * ReportsController
 * <p>
 *     All report related endpoints
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@Tag(name = " Reports", description = "Available Report Endpoints")
public class ReportsController {
    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;
    private final TokenContextFacade tokenContextFacade;

    public ReportsController(
            ReportCommandService reportCommandService,
            ReportQueryService reportQueryService,
            TokenContextFacade tokenContextFacade
    ) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
        this.tokenContextFacade = tokenContextFacade;
    }

    /**
     * Create a new report
     *
     */
    @PostMapping("/organizations/{organizationId}/plots/{plotId}/reports")
    @Operation(summary = " Create a new  report", description = " Create a new report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report created"),
            @ApiResponse(responseCode= "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = " Report not found")})
    public ResponseEntity<ReportResource> createReport(
            @PathVariable Long organizationId,
            @PathVariable Long plotId,
            @RequestBody CreateReportResource resource,
            HttpServletRequest request) {
        Long profileId = tokenContextFacade.extractUserIdFromToken(request);

        if (profileId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var createReportCommand = CreateReportCommandFromResourceAssembler.toCommandFromResource(
                resource,
                organizationId,
                plotId,
                profileId
        );
        var reportId = reportCommandService.handle(createReportCommand);

        if(reportId == null || reportId == 0L) return ResponseEntity.badRequest().build();

        var getReportByIdQuery = new GetReportByIdQuery(reportId);
        var report = reportQueryService.handle(getReportByIdQuery);
        if(report.isEmpty()) return ResponseEntity.notFound().build();
        var reportEntity = report.get();
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(reportEntity);
        return new ResponseEntity<>(reportResource, HttpStatus.CREATED);

    }

    /**
     * Get report by id
     *
     * @param reportId The report id
     * @return The {@link ReportResource} resource for the report
     */
    @GetMapping("reports/{reportId}")
    @Operation(summary = "Get Report by id", description = "Get report by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Report found"),
            @ApiResponse(responseCode = "404", description = "Report not found")})
    public ResponseEntity<ReportResource> getReportById(@PathVariable Long reportId) {
        var getReportByIdQuery = new GetReportByIdQuery(reportId);
        var report = reportQueryService.handle(getReportByIdQuery);
        if ( report.isEmpty()) return ResponseEntity.notFound().build();
        var reportEntity = report.get();
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(reportEntity);
        return ResponseEntity.ok(reportResource);
    }



}
