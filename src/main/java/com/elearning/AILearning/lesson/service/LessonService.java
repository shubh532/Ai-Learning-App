package com.elearning.AILearning.lesson.service;

import com.elearning.AILearning.lesson.dto.LessonResponse;
import com.elearning.AILearning.lesson.dto.SaveLessonDraftRequest;

import java.util.UUID;

public interface LessonService {

    LessonResponse generateLesson(UUID topicId);

    void publishLesson(UUID topicId);

    String draftLesson(SaveLessonDraftRequest request);

}
