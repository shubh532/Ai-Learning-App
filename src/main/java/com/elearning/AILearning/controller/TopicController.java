package com.elearning.AILearning.controller;

import com.elearning.AILearning.entity.Topic;
import com.elearning.AILearning.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public List<Topic> getTopicList(){
        return  topicService.getTopicList();    }
}
