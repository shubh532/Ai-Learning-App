package com.elearning.AILearning.controller;

import com.elearning.AILearning.dto.CustomUserDetails;
import com.elearning.AILearning.dto.request.ProfileUpdateRequest;
import com.elearning.AILearning.entity.UserProfile;
import com.elearning.AILearning.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserProfileService userProfileService;

    @GetMapping("/me")
    public String getUser() {
        return "Shubham";
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfile> updateProfile(
            @AuthenticationPrincipal CustomUserDetails user,
            @Valid @RequestBody ProfileUpdateRequest request
    ) {
        System.out.println("user " + user.getId());
        System.out.println("request " + request);
        UserProfile updated = userProfileService.updateProfile(user.getId(), request);
        return ResponseEntity.ok(updated);
    }

}
