//package com.elearning.AILearning.repository;
//
//import com.elearning.AILearning.entity.TopicLesson;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface TopicLessonRepository extends JpaRepository<TopicLesson, UUID> {
//
//    @Query("SELECT MAX(tl.version) FROM TopicLesson tl WHERE tl.topic.id = :topicId")
//    Optional<Integer> findMaxVersionByTopicId(@Param("topicId") UUID topicId);
//
//    Optional<TopicLesson> findByTopicIdAndIsActiveTrue(UUID topicId);
//}