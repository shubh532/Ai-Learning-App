package com.elearning.AILearning.lesson.service;

import com.elearning.AILearning.exception.ResourceNotFoundException;
import com.elearning.AILearning.lesson.dto.LessonResponse;
import com.elearning.AILearning.lesson.repository.LessonRepository;
import com.elearning.AILearning.topic.entity.Topic;
import com.elearning.AILearning.topic.entity.TopicLesson;
import com.elearning.AILearning.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LessonServiceImpl
        implements LessonService {

    private final TopicRepository topicRepository;

    private final LessonRepository lessonRepository;

    @Override
    public LessonResponse getLesson(
            String topicSlug
    ) {

        Topic topic = topicRepository
                .findBySlug(topicSlug)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found: " + topicSlug
                        )
                );

        TopicLesson lesson = lessonRepository
                .findByTopicIdAndIsActiveTrue(
                        topic.getId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found for topic: " + topicSlug
                        )
                );

        return LessonResponse.builder()
                .lessonId(lesson.getId())
                .topicId(topic.getId())
                .topicTitle(topic.getTitle())
                .content(lesson.getSections())
                .build();
    }
}
