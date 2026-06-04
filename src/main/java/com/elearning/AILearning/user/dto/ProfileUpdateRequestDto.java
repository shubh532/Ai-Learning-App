package com.elearning.AILearning.user.dto;

import com.elearning.AILearning.enums.CodeLanguage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProfileUpdateRequestDto {

    private String targetCompanyTier;

    @Min(value = 0, message = "Experience cannot be negative")
    private Integer experienceYears;

    @NotNull(message = "Target role required.")
    @Size(max = 100, message = "Role should not be more than 100 char")
    private String targetRole;

    @NotNull(message = "Preferred language is required")
    private CodeLanguage preferredLanguage;

    @Min(value = 1, message = "Weekly goal must be at least 1 session")
    @Max(value = 7, message = "Weekly goal capped at 7 sessions")
    private Integer weeklyGoal;

    private List<String> weakTopics;

}
