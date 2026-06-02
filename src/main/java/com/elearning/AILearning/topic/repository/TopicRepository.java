package com.elearning.AILearning.topic.repository;

import com.elearning.AILearning.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Optional<Topic> findBySlug(String slug);

    @Query("""
            SELECT t
            FROM Topic t
            WHERE NOT EXISTS (
                SELECT 1
                FROM TopicRelation tr
                WHERE tr.childTopic = t
                  AND tr.relationType = 'CONTAINS'
            )
            """)
    List<Topic> findRootTopics();
}
