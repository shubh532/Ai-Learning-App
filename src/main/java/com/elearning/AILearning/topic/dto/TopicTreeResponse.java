package com.elearning.AILearning.topic.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicTreeResponse {

    private UUID id;

    private String slug;

    private String title;

    private List<ChildTopicResponse> children;
}
