package com.elearning.AILearning.topic.dto;

import com.elearning.AILearning.enums.DifficultyLevel;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChildTopicResponse {

    private UUID id;

    private String slug;

    private String title;

    private DifficultyLevel difficulty;
}
