package com.elearning.AILearning.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.elearning.AILearning.entity.Topic;
import com.elearning.AILearning.entity.TopicLesson;
import com.elearning.AILearning.repository.TopicRepository;
import com.elearning.AILearning.repository.TopicLessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentServices {

    private final TopicRepository topicRepository;
    private final TopicLessonRepository lessonRepository;
    private final GeminiService geminiService;
    private final ObjectMapper objectMapper;

    @Transactional
    public UUID generateAndSaveLesson(UUID topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found with ID: " + topicId));

        String systemInstruction = "You are an expert technical content creator for an elite SDE2 interview prep platform. " +
                "You must output a strict JSON array of objects. Do not wrap the response in markdown blocks like ```json. " +
                "Each object in the array must contain exactly these keys: 'order' (int), 'title' (string), " +
                "'content_md' (string formatted in markdown), 'code_example' (string), and 'read_time_mins' (int).";

        String userPrompt = String.format(
                "Generate a highly detailed, comprehensive roadmap module lesson for the topic: '%s' under the category '%s'. " +
                        "Focus on production-grade design traps, edge cases, space/time complexities, and clear code implementations.",
                topic.getTitle(), topic.getTopicCategory()
        );

        log.info("Dispatching content generation request to Gemini for topic: {}", topic.getTitle());

        String rawApiResponse = geminiService.generateContent(systemInstruction, userPrompt);

        System.out.println("rawApiResponse: "+rawApiResponse);

        try {
            JsonNode rootNode = objectMapper.readTree(rawApiResponse);
            JsonNode candidateContent = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text");

            String structuredSectionsJson = candidateContent.asText();

            int latestVersion = lessonRepository.findMaxVersionByTopicId(topicId).orElse(0);

            TopicLesson newLesson = new TopicLesson();
            newLesson.setTopic(topic);
            newLesson.setVersion((short) (latestVersion + 1));
            newLesson.setStatus("DRAFT");
            newLesson.setIsActive(false);

            JsonNode sectionsNode = objectMapper.readTree(structuredSectionsJson);
            newLesson.setSections(sectionsNode);

            TopicLesson savedLesson = lessonRepository.save(newLesson);
            log.info("Successfully generated and saved lesson draft ID: {} with version: {}", savedLesson.getId(), newLesson.getVersion());

            return savedLesson.getId();

        } catch (Exception e) {
            log.error("Failed to parse or process Gemini payload response: ", e);
            throw new RuntimeException("Content processing lifecycle aborted due to parsing anomalies.", e);
        }
    }
}