package com.elearning.AILearning.aicontent;

import com.elearning.AILearning.lesson.dto.LessonContentDto;
import com.elearning.AILearning.topic.entity.Topic;
import com.fasterxml.jackson.databind.JsonNode;

public interface AIContentGenerator {

    JsonNode generateLesson(
            Topic topic
    );

    String testPrompt();
}
