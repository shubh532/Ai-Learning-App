package com.elearning.AILearning.lesson.repository;

import com.elearning.AILearning.topic.entity.TopicLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonRepository
        extends JpaRepository<TopicLesson, UUID> {

    Optional<TopicLesson> findByTopicIdAndIsActiveTrue(
            UUID topicId
    );
}