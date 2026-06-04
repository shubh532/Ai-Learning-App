package com.elearning.AILearning.user.controller;

import com.elearning.AILearning.user.dto.CustomUserDetailsDto;
import com.elearning.AILearning.user.dto.ProfileUpdateRequestDto;
import com.elearning.AILearning.user.entity.User;
import com.elearning.AILearning.user.entity.UserProfile;
import com.elearning.AILearning.user.service.AuthService;
import com.elearning.AILearning.user.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserProfileService userProfileService;
    private final AuthService authService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@AuthenticationPrincipal CustomUserDetailsDto user) {
        User profile = authService.getUser(user.getId());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfile> updateProfile(
            @AuthenticationPrincipal CustomUserDetailsDto user,
            @Valid @RequestBody ProfileUpdateRequestDto request
    ) {
        UserProfile updated = userProfileService.updateProfile(user.getId(), request);
        return ResponseEntity.ok(updated);
    }

}
