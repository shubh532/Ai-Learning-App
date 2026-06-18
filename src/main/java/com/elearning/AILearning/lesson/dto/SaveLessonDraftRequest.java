package com.elearning.AILearning.lesson.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveLessonDraftRequest {

    private UUID topicId;

    private JsonNode content;

    private String aiModelUsed;
}