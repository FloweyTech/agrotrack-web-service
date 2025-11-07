package com.floweytech.agrotrack.platform.profile.interfaces.rest;

import com.floweytech.agrotrack.platform.profile.domain.model.valueobjects.ProfileId;
import com.floweytech.agrotrack.platform.profile.domain.model.valueobjects.UserId;
import com.floweytech.agrotrack.platform.profile.domain.services.ProfileCommandService;
import com.floweytech.agrotrack.platform.profile.domain.services.ProfileQueryService;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.resources.ProfileResource;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.resources.UpdatePersonNameResource;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.resources.UpdatePhotoUrlResource;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.transform.UpdatePersonNameCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.profile.interfaces.rest.transform.UpdatePhotoUrlCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfileController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @Operation(summary = "Get profile by profile id", description = "Get a profile by its profile id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile found"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileByProfileId(@PathVariable Long profileId) {
        var profile = profileQueryService.getByProfileId(new ProfileId(profileId));
        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @Operation(summary = "Get profile by user id", description = "Get a profile by its user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile found"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId) {
        var profile = profileQueryService.getByUserId(new UserId(userId));
        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @Operation(summary = "Get profile by person name", description = "Get a profile by first name and last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile found"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/search")
    public ResponseEntity<ProfileResource> getProfileByPersonName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        var profile = profileQueryService.getByPersonName(firstName, lastName);
        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @Operation(summary = "Update person name", description = "Update the person name of a profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person name updated"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PutMapping("/{profileId}/person-name")
    public ResponseEntity<ProfileResource> updatePersonName(
            @PathVariable Long profileId,
            @RequestBody UpdatePersonNameResource resource) {
        var command = UpdatePersonNameCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
        var profile = profileCommandService.handle(command);
        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @Operation(summary = "Update photo URL", description = "Update the photo URL of a profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo URL updated"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PutMapping("/{profileId}/photo-url")
    public ResponseEntity<ProfileResource> updatePhotoUrl(
            @PathVariable Long profileId,
            @RequestBody UpdatePhotoUrlResource resource) {
        var command = UpdatePhotoUrlCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
        var profile = profileCommandService.handle(command);
        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }
}


