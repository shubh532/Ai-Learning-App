//package com.elearning.AILearning.entity;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.JdbcTypeCode;
//import org.hibernate.type.SqlTypes;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "topic_lessons")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class TopicLesson {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "topic_id", nullable = false)
//    private Topic topic;
//
//    private Short version;
//
//    @Column(length = 20)
//    private String status;
//
//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "sections", columnDefinition = "jsonb")
//    private JsonNode sections;
//
//    private Boolean isActive;
//
//    @Column(length = 50)
//    private String aiModelUsed;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reviewed_by")
//    private User reviewedBy;
//
//    private LocalDateTime publishedAt;
//}