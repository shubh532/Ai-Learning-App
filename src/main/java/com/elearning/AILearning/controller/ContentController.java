package com.elearning.AILearning.controller;

import com.elearning.AILearning.service.ContentServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@Slf4j
public class ContentController {

    private final ContentServices contentService;

    /**
     * POST /api/admin/lessons/generate
     * Triggers Gemini to generate a structured lesson draft for a specific topic.
     * * Request body example: { "topicId": "8fc85a4f-9d8a-4fa4-838c-1ee26d5f2e3f" }
     */


    @PostMapping("/generate")
    public ResponseEntity<Map<String, Object>> generateLessonDraft(@RequestBody Map<String, String> requestBody) {
        String topicIdStr = requestBody.get("topicId");
        if (topicIdStr == null || topicIdStr.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing required field: topicId"));
        }

        try {
            UUID topicId = UUID.fromString(topicIdStr);
            log.info("Admin triggered lesson generation for topicId: {}", topicId);

            UUID draftId = contentService.generateAndSaveLesson(topicId);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", "Lesson draft generated successfully via Gemini.",
                    "lessonDraftId", draftId
            ));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid UUID format for topicId."));
        } catch (Exception e) {
            log.error("Error processing admin generation request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}