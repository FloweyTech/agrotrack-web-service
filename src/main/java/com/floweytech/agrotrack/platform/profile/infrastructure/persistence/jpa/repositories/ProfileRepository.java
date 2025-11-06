package com.floweytech.agrotrack.platform.profile.infrastructure.persistence.jpa.repositories;

import com.floweytech.agrotrack.platform.profile.domain.model.aggregates.Profile;
import com.floweytech.agrotrack.platform.profile.domain.model.valueobjects.ProfileId;
import com.floweytech.agrotrack.platform.profile.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByProfileId(ProfileId profileId);
    Optional<Profile> findByUserId(UserId userId);
    Optional<Profile> findByPersonName_FirstNameAndPersonName_LastName(String firstName, String lastName);
    boolean existsByProfileId(ProfileId profileId);
    boolean existsByUserId(UserId userId);
}
