package com.elearning.AILearning.service;

import com.elearning.AILearning.dto.request.ProfileUpdateRequest;
import com.elearning.AILearning.entity.User;
import com.elearning.AILearning.entity.UserProfile;
import com.elearning.AILearning.repository.UserProfileRepository;
import com.elearning.AILearning.repository.UserRepository;
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
    public UserProfile updateProfile(UUID userId, ProfileUpdateRequest req) {

        UserProfile profile = getProfile(userId);

        applyUpdates(profile, req);
        profile.setOnboardingDone(true);

        return userProfileRepo.save(profile);
    }

    private void applyUpdates(UserProfile profile, ProfileUpdateRequest req) {
        profile.setTargetCompanyTier(req.getTargetCompanyTier());
        profile.setExperienceYears(req.getExperienceYears());
        profile.setTargetRole(req.getTargetRole());
        profile.setPreferredLanguage(req.getPreferredLanguage());
        profile.setWeeklyGoal(req.getWeeklyGoal());
        profile.setWeakTopics(
                req.getWeakTopics() != null ? req.getWeakTopics() : new ArrayList<>()
        );
    }
}