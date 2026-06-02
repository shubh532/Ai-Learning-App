//package com.elearning.AILearning.entity;
//
//import com.elearning.AILearning.enums.*;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//import java.util.UUID;
//
//@Entity
//@Table(name = "topics")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Topic {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "id")
//    private UUID id;
//
//    @Column(name = "parent_id")
//    private UUID parentId;
//
//    @Column(unique = true, nullable = false)
//    private String slug;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = false)
//    private String description;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "topic_category")
//    private TopicCategory topicCategory;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "topic_type")
//    private TopicType topicType;
//
//    @Enumerated(EnumType.STRING)
//    private DifficultyLevel difficulty;
//
//
//    @Column(name = "is_published")
//    private boolean isPublished = false;
//
//    @Column(name = "is_standalone")
//    private boolean isStandalone = false;
//
//    @Column(name = "display_order")
//    private Integer displayOrder;
//
//    @Column(name = "estimated_time")
//    private Integer estimatedTime;
//}