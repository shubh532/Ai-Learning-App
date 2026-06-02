package com.elearning.AILearning.topic.service;

import com.elearning.AILearning.topic.dto.TopicResponse;
import com.elearning.AILearning.topic.dto.TopicSummaryResponse;

import java.util.List;

public interface TopicService {

    List<TopicSummaryResponse> getAllTopics();

    TopicResponse getTopicBySlug(String slug);
}
