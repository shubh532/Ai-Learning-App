package com.elearning.AILearning.topic.service;

import com.elearning.AILearning.exception.ResourceNotFoundException;
import com.elearning.AILearning.topic.dto.ChildTopicResponse;
import com.elearning.AILearning.topic.dto.TopicResponse;
import com.elearning.AILearning.topic.dto.TopicSummaryResponse;
import com.elearning.AILearning.topic.dto.TopicTreeResponse;
import com.elearning.AILearning.topic.entity.Topic;
import com.elearning.AILearning.topic.entity.TopicRelation;
import com.elearning.AILearning.topic.repository.TopicRelationRepository;
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
    private final TopicRelationRepository topicRelationRepository;


    private TopicSummaryResponse mapToSummary(Topic topic) {

        return TopicSummaryResponse.builder()
                .id(topic.getId())
                .slug(topic.getSlug())
                .title(topic.getTitle())
                .build();
    }

    @Override
    public List<TopicSummaryResponse> getRootTopics() {

        return topicRepository.findRootTopics()
                .stream()
                .map(this::mapToSummary)
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

    @Override
    public List<ChildTopicResponse> getChildren(String slug) {

        Topic parent = topicRepository.findBySlug(slug)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found: " + slug
                        ));

        return topicRelationRepository
                .findChildren(parent.getId())
                .stream()
                .map(relation ->
                        ChildTopicResponse.builder()
                                .id(relation.getChildTopic().getId())
                                .slug(relation.getChildTopic().getSlug())
                                .title(relation.getChildTopic().getTitle())
                                .difficulty(relation.getChildTopic().getDifficulty())
                                .build())
                .toList();
    }

    private ChildTopicResponse mapToChildResponse(
            TopicRelation relation
    ) {

        Topic child = relation.getChildTopic();

        return ChildTopicResponse.builder()
                .id(child.getId())
                .slug(child.getSlug())
                .title(child.getTitle())
                .difficulty(child.getDifficulty())
                .build();
    }

    @Override
    public List<TopicTreeResponse> getTopicTree() {

        List<Topic> rootTopics = topicRepository.findRootTopics();

        return rootTopics.stream()
                .map(root -> {

                    List<ChildTopicResponse> children =
                            topicRelationRepository
                                    .findChildren(root.getId())
                                    .stream()
                                    .map(this::mapToChildResponse)
                                    .toList();

                    return TopicTreeResponse.builder()
                            .id(root.getId())
                            .slug(root.getSlug())
                            .title(root.getTitle())
                            .children(children)
                            .build();
                })
                .toList();
    }
}