package com.elearning.AILearning.entity;

import com.elearning.AILearning.enums.Difficulty;
import com.elearning.AILearning.enums.TopicCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id") // Matches pgAdmin [PK] uuid
    private UUID id;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(name = "topic_category")
    private TopicCategory topicCategory;

    @Column(name = "is_published")
    private boolean isPublished = false;

    @Column(name = "display_order") // Added to handle the sorting logic
    private Integer displayOrder;
}