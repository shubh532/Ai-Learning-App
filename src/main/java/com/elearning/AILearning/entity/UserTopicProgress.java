package com.elearning.AILearning.entity;

import com.elearning.AILearning.enums.LearnState;
import com.elearning.AILearning.enums.MasteryState;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_topic_progress",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_user_topic", columnNames = {"user_id", "topic_id"})
        }
)
@Data
public class UserTopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(name = "learn_state")
    private LearnState learnState = LearnState.NOT_STARTED;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "sections_read", columnDefinition = "jsonb")
    private List<Integer> sectionsRead = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "mastery_state")
    private MasteryState masteryState = MasteryState.NOT_STARTED;

    @Column(name = "quiz_best_score", precision = 4, scale = 2)
    private BigDecimal quizBestScore;

    @Column(name = "last_visited_at")
    private LocalDateTime lastVisitedAt;
}