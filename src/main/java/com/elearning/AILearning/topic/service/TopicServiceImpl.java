package com.elearning.AILearning.topic.service;

import com.elearning.AILearning.exception.ResourceNotFoundException;
import com.elearning.AILearning.topic.dto.TopicResponse;
import com.elearning.AILearning.topic.dto.TopicSummaryResponse;
import com.elearning.AILearning.topic.entity.Topic;
import com.elearning.AILearning.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public List<TopicSummaryResponse> getAllTopics() {

        return topicRepository.findAll()
                .stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    @Override
    public TopicResponse getTopicBySlug(String slug) {

        Topic topic = topicRepository.findBySlug(slug)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Topic not found: " + slug
                        )
                );

        return toResponse(topic);
    }

    private TopicSummaryResponse toSummaryResponse(Topic topic) {

        return TopicSummaryResponse.builder()
                .id(topic.getId())
                .slug(topic.getSlug())
                .title(topic.getTitle())
                .build();
    }

    private TopicResponse toResponse(Topic topic) {

        return TopicResponse.builder()
                .id(topic.getId())
                .slug(topic.getSlug())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .difficulty(topic.getDifficulty())
                .isStandalone(topic.getIsStandalone())
                .isPublished(topic.getIsPublished())
                .estimatedMins(topic.getEstimatedMins())
                .build();
    }
}