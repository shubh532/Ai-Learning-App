package com.elearning.AILearning.user.service;

import com.elearning.AILearning.user.dto.ProfileUpdateRequestDto;
import com.elearning.AILearning.user.entity.UserProfile;
import com.elearning.AILearning.user.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepo;

    public UserProfile getProfile(UUID userId) {
        return userProfileRepo.findProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found."));
    }

    @Transactional
    public UserProfile updateProfile(UUID userId, ProfileUpdateRequestDto req) {

        UserProfile profile = getProfile(userId);

        System.out.println("profile: " + profile);

        applyUpdates(profile, req);
        profile.setOnboardingDone(true);

        return userProfileRepo.save(profile);
    }

    private void applyUpdates(UserProfile profile, ProfileUpdateRequestDto req) {
        profile.setTargetCompanyTier(req.getTargetCompanyTier());
        profile.setExperienceYears(req.getExperienceYears());
        profile.setTargetRole(req.getTargetRole());
        profile.setPreferredLanguage(req.getPreferredLanguage());
        profile.setWeeklyGoal(req.getWeeklyGoal());
        profile.setWeakTopics(
                req.getWeakTopics() != null ? req.getWeakTopics() : new ArrayList<>());
    }
}