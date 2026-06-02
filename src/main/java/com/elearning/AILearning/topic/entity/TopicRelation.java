package com.elearning.AILearning.topic.entity;

import com.elearning.AILearning.enums.TopicRelationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "topic_relations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicRelation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_topic_id", nullable = false)
    private Topic parentTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_topic_id", nullable = false)
    private Topic childTopic;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation_type", nullable = false)
    private TopicRelationType relationType;

    @Column(name = "display_order")
    private Short displayOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}