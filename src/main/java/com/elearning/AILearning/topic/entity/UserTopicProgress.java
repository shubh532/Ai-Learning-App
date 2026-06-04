package com.elearning.AILearning.topic.entity;

import com.elearning.AILearning.user.entity.User;
import com.elearning.AILearning.enums.LearnStateType;
import com.elearning.AILearning.enums.MasteryStateType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "user_topic_progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_topic_progress_user_topic",
                        columnNames = {"user_id", "topic_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTopicProgress {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(name = "learn_state", nullable = false)
    private LearnStateType learnState;

    @Enumerated(EnumType.STRING)
    @Column(name = "mastery_state", nullable = false)
    private MasteryStateType masteryState;

    @Builder.Default
    @Column(nullable = false)
    private Boolean unlocked = true;

    @Column(name = "quiz_best_score", precision = 4, scale = 2)
    private BigDecimal quizBestScore;

    @Column(name = "last_visited_at")
    private LocalDateTime lastVisitedAt;

    @Column(name = "sections_read")
    private List<Short> sectionsRead;

    @Column(columnDefinition = "jsonb")
    private String highlights;

    @Column(columnDefinition = "jsonb")
    private String notes;
}