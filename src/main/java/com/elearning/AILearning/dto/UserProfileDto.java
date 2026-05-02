package com.elearning.AILearning.dto;

import com.elearning.AILearning.enums.CodeLanguage;
import com.elearning.AILearning.enums.UserPlan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserProfileDto {

    UUID id;
    String targetCompanyTier;
    Integer experienceYears;
    String targetRole;
    String preferredLanguage;
    Integer weeklyGoal;
    List<String> weakTopics;
    boolean onboardingDone;
    LocalDateTime updatedAt;
}