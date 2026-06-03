package com.elearning.AILearning.lesson.dto;

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

    private LessonContentDto content;
}