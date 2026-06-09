package com.elearning.AILearning.topic.entity;

import com.elearning.AILearning.user.entity.User;
import com.elearning.AILearning.enums.LessonStatus;
import com.elearning.AILearning.lesson.dto.LessonContentDto;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "topic_lessons",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_topic_version",
                        columnNames = {"topic_id", "version"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "topic_id",
            foreignKey = @ForeignKey(name = "fk_topic_lesson_topic")
    )
    private Topic topic;

    @Column(nullable = false)
    private Integer version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LessonStatus status;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode sections;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(length = 50)
    private String aiModelUsed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "reviewed_by",
            foreignKey = @ForeignKey(name = "fk_topic_lesson_reviewer")
    )
    private User reviewedBy;

    private LocalDateTime publishedAt;
}