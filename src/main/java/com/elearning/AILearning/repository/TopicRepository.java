package com.elearning.AILearning.repository;

import com.elearning.AILearning.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    // For Roadmaps: Fetch children for a parent (e.g., all Array sub-topics)
    List<Topic> findByParentIdInOrderByDisplayOrderAsc(List<UUID> parentIds);

    // For Main Catalogue: Fetch all top-level or standalone modules
    List<Topic> findByIsPublishedTrueAndIsStandaloneTrueOrderByDisplayOrderAsc();
}
