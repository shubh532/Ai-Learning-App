package com.elearning.AILearning.lesson.service;

import com.elearning.AILearning.lesson.dto.LessonResponse;

import java.util.UUID;

public interface LessonService {

    LessonResponse generateAndSaveLesson(UUID topicId);
    void publishLesson(UUID topicId);

}
