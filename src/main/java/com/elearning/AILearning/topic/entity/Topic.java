package com.elearning.AILearning.topic.entity;

import com.elearning.AILearning.enums.DifficultyLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    private UUID id;

    private String slug;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;

    private Boolean isStandalone;

    private Boolean isPublished;

    private Short estimatedMins;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
