package com.elearning.AILearning.topic.dto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicSummaryResponse {

    private UUID id;

    private String slug;

    private String title;
}