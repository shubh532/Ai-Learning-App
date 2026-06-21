package com.elearning.AILearning.lesson.controller;

import com.elearning.AILearning.lesson.dto.LessonResponse;
import com.elearning.AILearning.lesson.dto.SaveLessonDraftRequest;
import com.elearning.AILearning.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/lessons")
public class AdminLessonController {

    private final LessonService lessonService;

    @PostMapping("/generate-preview/{topicId}")
    public ResponseEntity<LessonResponse>
    generateLesson(@PathVariable UUID topicId) {
        LessonResponse response = lessonService.generateLesson(topicId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> draftLesson(@RequestBody SaveLessonDraftRequest request) {
        String message = lessonService.draftLesson(request);
        return ResponseEntity.ok(message);
    }
}
