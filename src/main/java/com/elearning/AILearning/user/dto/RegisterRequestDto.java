package com.elearning.AILearning.user.dto;

import com.elearning.AILearning.enums.AuthProvider;
import com.elearning.AILearning.enums.UserPlan;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid mail format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$",
            message = "Password must include upper, lower, number, special character"
    )
    private String password;

    @NotNull(message = "Auth provider is required")
    private AuthProvider authProvider;

    @NotBlank(message = "Name is required")
    private String displayName;

    private Boolean guest = false;

    @NotNull(message = "Plan is required")
    private UserPlan plan = UserPlan.FREE;
}