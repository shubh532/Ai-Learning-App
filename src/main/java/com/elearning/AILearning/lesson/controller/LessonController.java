package com.elearning.AILearning.lesson.controller;

import com.elearning.AILearning.lesson.dto.LessonResponse;
import com.elearning.AILearning.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{slug}/lesson")
    public ResponseEntity<LessonResponse> getLesson(
            @PathVariable String slug
    ) {

        System.out.println("Slug: " + slug);
        return ResponseEntity.ok(
                lessonService.getLesson(slug)
        );
    }
}
