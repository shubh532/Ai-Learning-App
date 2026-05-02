package com.elearning.AILearning.entity;

import com.elearning.AILearning.enums.CodeLanguage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "target_company_tier")
    private String targetCompanyTier;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "target_role")
    private String targetRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_language")
    private CodeLanguage preferredLanguage;

    @Builder.Default
    private List<String> weakTopics = new ArrayList<>();

    @Column(name = "onboarding_done")
    private boolean onboardingDone = false;

    @Column(name = "weekly_goal")
    private Integer weeklyGoal = 5;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}