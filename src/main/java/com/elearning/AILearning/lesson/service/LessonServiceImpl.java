package com.elearning.AILearning.lesson.service;

import com.elearning.AILearning.aicontent.AIContentGenerator;
import com.elearning.AILearning.enums.LessonStatus;
import com.elearning.AILearning.exception.ResourceNotFoundException;
import com.elearning.AILearning.lesson.dto.LessonResponse;
import com.elearning.AILearning.topic.entity.Topic;
import com.elearning.AILearning.topic.entity.TopicLesson;
import com.elearning.AILearning.topic.repository.TopicLessonRepository;
import com.elearning.AILearning.topic.repository.TopicRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonServiceImpl
        implements LessonService {

    private final TopicRepository topicRepository;
    private final TopicLessonRepository lessonRepository;
    private final AIContentGenerator aiContentGenerator;

    @Override
    public LessonResponse generateAndSaveLesson(UUID topicId) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found"));

        JsonNode content =
                aiContentGenerator.generateLesson(topic);

        Integer nextVersion =
                getNextVersion(topicId);

        TopicLesson lesson =
                TopicLesson.builder()
                        .topic(topic)
                        .version(nextVersion)
                        .status(LessonStatus.DRAFT)
                        .sections(content)
                        .isActive(false)
                        .aiModelUsed("gemini")
                        .build();

//        lesson = lessonRepository.save(lesson);

        System.out.println("Lesson Service: "+ lesson.toString());
        return LessonResponse.builder()
                .lessonId(lesson.getId())
                .topicId(topic.getId())
                .topicTitle(topic.getTitle())
                .content(content)
                .build();
    }

    private Integer getNextVersion(UUID topicId) {

        return lessonRepository
                .findTopByTopicIdOrderByVersionDesc(topicId)
                .map(lesson -> lesson.getVersion() + 1)
                .orElse(1);
    }

    @Transactional
    public void publishLesson(
            UUID lessonId
    ) {

        TopicLesson lesson =
                lessonRepository.findById(
                        lessonId
                ).orElseThrow();

        lessonRepository
                .deactivateAllLessonsForTopic(
                        lesson.getTopic().getId()
                );

        lesson.setIsActive(true);
        lesson.setStatus(
                LessonStatus.PUBLISHED
        );
        lesson.setPublishedAt(
                LocalDateTime.now()
        );
    }
}