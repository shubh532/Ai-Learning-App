package com.elearning.AILearning.topic.entity;

import com.elearning.AILearning.entity.User;
import com.elearning.AILearning.enums.LessonStatus;
import jakarta.persistence.*;
import lombok.*;

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
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_topic_lesson_topic")
    )
    private com.elearning.AILearning.topic.entity.Topic topic;

    @Column(nullable = false)
    private Short version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LessonStatus status;

    @Column(
            columnDefinition = "jsonb",
            nullable = false
    )
    private String sections;

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