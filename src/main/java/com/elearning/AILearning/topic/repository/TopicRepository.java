package com.elearning.AILearning.topic.repository;

import com.elearning.AILearning.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Optional<Topic> findBySlug(String slug);
}
