package com.elearning.AILearning.aicontent.gemini;

import com.elearning.AILearning.aicontent.AIContentGenerator;
import com.elearning.AILearning.aicontent.contentdto.ContentDto;
import com.elearning.AILearning.aicontent.contentdto.PartDto;
import com.elearning.AILearning.topic.entity.Topic;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiContentGenerator implements AIContentGenerator {

    private final WebClient geminiWebClient;
    private final GeminiProperties geminiProperties;
    private final ObjectMapper objectMapper;

    @Override
    public String testPrompt() {

        GeminiRequestDto request =
                GeminiRequestDto.builder()
                        .contents(
                                List.of(
                                        ContentDto.builder()
                                                .parts(
                                                        List.of(
                                                                PartDto.builder()
                                                                        .text("Explain Arrays in one sentence.")
                                                                        .build()
                                                        )
                                                )
                                                .build()
                                )
                        )
                        .build();

        GeminiResponseDto response =
                geminiWebClient.post()
                        .uri(
                                "/models/"
                                        + geminiProperties.getModel()
                                        + ":generateContent?key="
                                        + geminiProperties.getApiKey()
                        )
                        .bodyValue(request)
                        .retrieve()
                        .onStatus(
                                HttpStatusCode::isError,
                                gemRes ->
                                        gemRes
                                                .bodyToMono(String.class)
                                                .flatMap(errBody -> {
                                                    log.error("Gemini Error: {}", errBody);
                                                    return Mono.error(
                                                            new RuntimeException(
                                                                    "Gemini API Error: " + gemRes.statusCode())
                                                    );
                                                })
                        )
                        .bodyToMono(GeminiResponseDto.class)
                        .block();

        return response.getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();
    }

    @Override
    public JsonNode generateLesson(Topic topic) {

        String prompt = buildPrompt(topic);

        System.out.println("Gemini Prompt: " + prompt);

        GeminiRequestDto request =
                GeminiRequestDto.builder()
                        .contents(
                                List.of(
                                        ContentDto.builder()
                                                .parts(
                                                        List.of(
                                                                PartDto.builder()
                                                                        .text(prompt)
                                                                        .build()
                                                        )
                                                )
                                                .build()
                                )
                        )
                        .build();

        GeminiResponseDto response =
                geminiWebClient.post()
                        .uri(
                                "/models/"
                                        + geminiProperties.getModel()
                                        + ":generateContent?key="
                                        + geminiProperties.getApiKey()
                        )
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(GeminiResponseDto.class)
                        .block();

        if (response == null
                || response.getCandidates() == null
                || response.getCandidates().isEmpty()) {

            throw new RuntimeException("No response returned from Gemini");
        }

        String lessonJson =
                response.getCandidates()
                        .get(0)
                        .getContent()
                        .getParts()
                        .get(0)
                        .getText();

        log.info("Raw Gemini Response:\n{}", lessonJson);

        try {

            lessonJson = lessonJson
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            JsonNode lessonNode =
                    objectMapper.readTree(lessonJson);

            return lessonNode;

        } catch (Exception ex) {

            log.error(
                    "Failed to parse Gemini response:\n{}",
                    lessonJson,
                    ex
            );

            throw new RuntimeException(
                    "Failed to parse lesson JSON",
                    ex
            );
        }
    }

    private String buildPrompt(Topic topic) {

        return """
                Generate a lesson about %s.
                
                Return JSON:
                
                {
                  "overview": {
                    "title": "",
                    "description": ""
                  },
                  "coreConcepts": []
                }
                
                Return only JSON.
                """
                .formatted(
                        topic.getTitle(),
                        topic.getDescription()
                );
    }
}