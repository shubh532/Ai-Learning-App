package com.elearning.AILearning.lesson.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonResponse {

    private UUID lessonId;

    private UUID topicId;

    private String topicTitle;
    private JsonNode content;
}