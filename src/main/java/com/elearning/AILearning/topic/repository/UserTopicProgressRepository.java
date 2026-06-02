package com.elearning.AILearning.topic.repository;

import com.elearning.AILearning.topic.entity.UserTopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTopicProgressRepository extends JpaRepository<UserTopicProgress, UUID> {
}
