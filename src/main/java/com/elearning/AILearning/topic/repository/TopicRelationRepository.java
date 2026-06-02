package com.elearning.AILearning.topic.repository;

import com.elearning.AILearning.topic.entity.TopicRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TopicRelationRepository extends JpaRepository<TopicRelation, UUID> {

    @Query("""
    SELECT tr
    FROM TopicRelation tr
    JOIN FETCH tr.childTopic
    WHERE tr.parentTopic.id = :parentId
    ORDER BY tr.displayOrder
""")
    List<TopicRelation> findChildren(UUID parentId);
}
