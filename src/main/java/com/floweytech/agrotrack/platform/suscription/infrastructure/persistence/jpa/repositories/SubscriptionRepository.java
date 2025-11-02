package com.floweytech.agrotrack.platform.suscription.infrastructure.persistence.jpa.repositories;

import com.floweytech.agrotrack.platform.suscription.domain.model.aggregates.Subscription;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionId;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionStatus;
import com.floweytech.agrotrack.platform.suscription.domain.model.valueobject.SubscriptionPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscriptionId(SubscriptionId subscriptionId);
    boolean existsBySubscriptionId(SubscriptionId subscriptionId);
    List<Subscription> findBySubscriptionStatus(SubscriptionStatus subscriptionStatus);
    List<Subscription> findBySubscriptionPlan(SubscriptionPlan subscriptionPlan);
    List<Subscription> findBySubscriptionStatusOrderByStartDateDesc(SubscriptionStatus subscriptionStatus);
}
