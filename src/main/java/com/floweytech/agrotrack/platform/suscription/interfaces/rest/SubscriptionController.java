package com.floweytech.agrotrack.platform.suscription.interfaces.rest;

import com.floweytech.agrotrack.platform.shared.interfaces.acl.TokenContextFacade;
import com.floweytech.agrotrack.platform.suscription.application.internal.outboundservices.acl.ExternalProfileService;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.ActivateSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.CancelSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.commands.ExpireSubscriptionCommand;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionId;
import com.floweytech.agrotrack.platform.suscription.domain.service.SubscriptionCommandService;
import com.floweytech.agrotrack.platform.suscription.domain.service.SubscriptionQueryService;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources.SubscriptionResource;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/subscriptions", produces = "application/json")
@Tag(name = "Subscriptions", description = "Subscription Management Endpoints")
public class SubscriptionController {

    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;
    private final TokenContextFacade tokenContextFacade;
    private final ExternalProfileService externalProfileService;

    public SubscriptionController(SubscriptionCommandService subscriptionCommandService,
                                   SubscriptionQueryService subscriptionQueryService,
                                   TokenContextFacade tokenContextFacade,
                                   ExternalProfileService externalProfileService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
        this.tokenContextFacade = tokenContextFacade;
        this.externalProfileService = externalProfileService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(
            @Valid @RequestBody CreateSubscriptionResource resource,
            HttpServletRequest request) {

        // Extract userId from JWT token in the request
        Long userId = tokenContextFacade.extractUserIdFromToken(request);

        // Get profileId from Profile context using ACL
        var profileIdOpt = externalProfileService.getProfileIdByUserId(userId);

        if (profileIdOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Long ownerProfileId = profileIdOpt.get();

        // Create command with the ownerProfileId obtained from ACL
        var command = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource, ownerProfileId);
        var subscriptionId = subscriptionCommandService.handle(command);

        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));

        return subscription.map(sub -> ResponseEntity.status(HttpStatus.CREATED)
                .body(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long subscriptionId) {
        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));

        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SubscriptionResource>> getActiveSubscriptions() {
        var subscriptions = subscriptionQueryService.getActiveSubscriptions();
        var resources = subscriptions.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{subscriptionId}/activate")
    public ResponseEntity<SubscriptionResource> activateSubscription(@PathVariable Long subscriptionId) {
        var command = new ActivateSubscriptionCommand(subscriptionId);
        var result = subscriptionCommandService.handle(command);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(result.get()));
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{subscriptionId}/cancel")
    public ResponseEntity<SubscriptionResource> cancelSubscription(@PathVariable Long subscriptionId) {
        var command = new CancelSubscriptionCommand(subscriptionId);
        var result = subscriptionCommandService.handle(command);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(result.get()));
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{subscriptionId}/expire")
    public ResponseEntity<SubscriptionResource> expireSubscription(@PathVariable Long subscriptionId) {
        var command = new ExpireSubscriptionCommand(subscriptionId);
        var result = subscriptionCommandService.handle(command);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(result.get()));
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.badRequest().build());
    }
}
