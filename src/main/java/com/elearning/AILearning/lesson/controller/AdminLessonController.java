package com.elearning.AILearning.lesson.controller;

import com.elearning.AILearning.lesson.dto.LessonResponse;
import com.elearning.AILearning.lesson.service.LessonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/lessons")
public class AdminLessonController {

    @GetMapping("/test-json")
    public JsonNode testJson() throws Exception {

        return new ObjectMapper().readTree("""
        {
          "name": "Arrays",
          "level": "Beginner"
        }
        """);
    }

    private final LessonService
            lessonGenerationService;

    @PostMapping("/generate/{topicId}")
    public ResponseEntity<LessonResponse>
    generateLesson(
            @PathVariable UUID topicId
    ) {

        return ResponseEntity.ok(
                lessonGenerationService
                        .generateAndSaveLesson(
                                topicId
                        )
        );
    }
}
