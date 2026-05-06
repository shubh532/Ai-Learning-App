package com.elearning.AILearning.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TopicSelectionRequest {
    List<UUID> topicIds;
}
