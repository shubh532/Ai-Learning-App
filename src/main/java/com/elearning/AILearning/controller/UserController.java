package com.elearning.AILearning.controller;

import com.elearning.AILearning.dto.CustomUserDetails;
import com.elearning.AILearning.dto.request.ProfileUpdateRequest;
import com.elearning.AILearning.entity.User;
import com.elearning.AILearning.entity.UserProfile;
import com.elearning.AILearning.repository.UserRepository;
import com.elearning.AILearning.service.AuthService;
import com.elearning.AILearning.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserProfileService userProfileService;
    private final AuthService authService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@AuthenticationPrincipal CustomUserDetails user) {
        User profile = authService.getUser(user.getId());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfile> updateProfile(
            @AuthenticationPrincipal CustomUserDetails user,
            @Valid @RequestBody ProfileUpdateRequest request
    ) {
        UserProfile updated = userProfileService.updateProfile(user.getId(), request);
        return ResponseEntity.ok(updated);
    }

}
