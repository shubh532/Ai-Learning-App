package com.elearning.AILearning.topic.repository;

import com.elearning.AILearning.topic.entity.TopicLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicLessonRepository extends JpaRepository<TopicLesson, UUID> {
    Optional<TopicLesson>
    findTopByTopicIdOrderByVersionDesc(UUID topicId);

    @Modifying
    @Query("""
       update TopicLesson tl
       set tl.isActive = false
       where tl.topic.id = :topicId
       """)
    void deactivateAllLessonsForTopic(UUID topicId);

}
