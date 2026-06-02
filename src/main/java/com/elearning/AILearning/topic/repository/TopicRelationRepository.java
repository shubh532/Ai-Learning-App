package com.elearning.AILearning.topic.repository;

import com.elearning.AILearning.topic.entity.TopicRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicRelationRepository extends JpaRepository<TopicRelation, UUID> {
}
