package com.elearning.AILearning.dto;

import com.elearning.AILearning.enums.AuthProvider;
import com.elearning.AILearning.enums.UserPlan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserProfileDto {

    private UUID id;
    private String email;
    private String displayName;

    private boolean emailVerified;
    private boolean guest;

    private UserPlan plan;
    private AuthProvider authProvider;

    private short sessionsToday;

    private LocalDateTime createdAt;
}
