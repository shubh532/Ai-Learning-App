package com.elearning.AILearning.topic.dto;

import com.elearning.AILearning.enums.DifficultyLevel;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponse {

    private UUID id;

    private String slug;

    private String title;

    private String description;

    private DifficultyLevel difficulty;

    private Boolean isStandalone;

    private Boolean isPublished;

    private Short estimatedMins;
}
