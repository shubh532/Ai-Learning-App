package com.elearning.AILearning.topic.controller;

import com.elearning.AILearning.topic.dto.ChildTopicResponse;
import com.elearning.AILearning.topic.dto.TopicResponse;
import com.elearning.AILearning.topic.dto.TopicSummaryResponse;
import com.elearning.AILearning.topic.dto.TopicTreeResponse;
import com.elearning.AILearning.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/root")
    public List<TopicSummaryResponse> getRootTopics() {
        return topicService.getRootTopics();
    }

    @GetMapping("/{slug}")
    public TopicResponse getTopic(
            @PathVariable String slug
    ) {
        return topicService.getTopicBySlug(slug);
    }

    @GetMapping("/{slug}/children")
    public List<ChildTopicResponse> getChildren(
            @PathVariable String slug
    ) {
        return topicService.getChildren(slug);
    }

    @GetMapping("/tree")
    public List<TopicTreeResponse> getTree() {
        return topicService.getTopicTree();
    }
    @GetMapping("/test")
    public String TopicTest() {
        return "TopicController v2 working";
    }
}