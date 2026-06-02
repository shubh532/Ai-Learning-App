package com.elearning.AILearning.topic.repository;

import com.elearning.AILearning.topic.entity.TopicLesson;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicLessonRepository extends JpaRepository<TopicLesson, UUID> {
}
