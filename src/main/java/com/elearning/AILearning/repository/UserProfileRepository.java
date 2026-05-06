package com.elearning.AILearning.repository;

import com.elearning.AILearning.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {


    Optional<UserProfile> findByUserId(UUID userId);

    @Query("SELECT p FROM UserProfile p WHERE p.user.id = :userId")
    Optional<UserProfile> findProfileByUserId(@Param("userId") UUID userId);

    boolean existsByUserId(UUID userId);

    @Query("""
    SELECT p.topic.id
    FROM UserTopicProgress p
    WHERE p.user.id = :userId
    AND p.topic.id IN :topicIds
""")
    Set<UUID> findExistingTopicIds(UUID userId, Set<UUID> topicIds);
}
