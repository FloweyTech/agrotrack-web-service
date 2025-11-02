package com.floweytech.agrotrack.platform.suscription.interfaces.rest;

import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionId;
import com.floweytech.agrotrack.platform.suscription.domain.service.SubscriptionCommandService;
import com.floweytech.agrotrack.platform.suscription.domain.service.SubscriptionQueryService;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.resources.SubscriptionResource;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.floweytech.agrotrack.platform.suscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public SubscriptionController(SubscriptionCommandService subscriptionCommandService,
                                   SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(@Valid @RequestBody CreateSubscriptionResource resource) {
        var command = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        subscriptionCommandService.handle(command);

        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(resource.subscriptionId()));

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
        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));

        if (subscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        subscription.get().activate();

        var updatedSubscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));
        return updatedSubscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{subscriptionId}/cancel")
    public ResponseEntity<SubscriptionResource> cancelSubscription(@PathVariable Long subscriptionId) {
        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));

        if (subscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        subscription.get().cancel();

        var updatedSubscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));
        return updatedSubscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{subscriptionId}/expire")
    public ResponseEntity<SubscriptionResource> expireSubscription(@PathVariable Long subscriptionId) {
        var subscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));

        if (subscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        subscription.get().expire();

        var updatedSubscription = subscriptionQueryService.getBySubscriptionId(new SubscriptionId(subscriptionId));
        return updatedSubscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElse(ResponseEntity.badRequest().build());
    }
}
