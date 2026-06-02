package com.elearning.AILearning.topic.service;

import com.elearning.AILearning.topic.dto.ChildTopicResponse;
import com.elearning.AILearning.topic.dto.TopicResponse;
import com.elearning.AILearning.topic.dto.TopicSummaryResponse;
import com.elearning.AILearning.topic.dto.TopicTreeResponse;

import java.util.List;

public interface TopicService {

    List<TopicSummaryResponse> getRootTopics();

    List<TopicTreeResponse> getTopicTree();

    TopicResponse getTopicBySlug(String slug);

    List<ChildTopicResponse> getChildren(String slug);
}
