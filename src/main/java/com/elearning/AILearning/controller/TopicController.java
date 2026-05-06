package com.elearning.AILearning.controller;

import com.elearning.AILearning.dto.CustomUserDetails;
import com.elearning.AILearning.dto.TopicSelectionRequest;
import com.elearning.AILearning.entity.Topic;
import com.elearning.AILearning.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public List<Topic> getTopicList() {
        return topicService.getTopicList();
    }

    @PostMapping("/select")
    public ResponseEntity<Void> selectTopics(
            @AuthenticationPrincipal CustomUserDetails user,
            @Valid @RequestBody TopicSelectionRequest request
    ) {
        topicService.enrollTopic(user.getId(), request.getTopicIds());
        return ResponseEntity.ok().build();
    }
}
