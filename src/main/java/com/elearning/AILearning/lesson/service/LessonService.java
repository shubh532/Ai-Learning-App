package com.elearning.AILearning.lesson.service;

import com.elearning.AILearning.lesson.dto.LessonResponse;

public interface LessonService {
    LessonResponse getLesson(
            String topicSlug
    );
}
