package com.elearning.AILearning.entity;

import com.elearning.AILearning.enums.AuthProvider;
import com.elearning.AILearning.enums.UserPlan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email"),
                @Index(name = "idx_user_google_id", columnList = "google_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false)
    private AuthProvider authProvider;

    @Column(name = "google_id", unique = true)
    private String googleId;

    @Column(name = "display_name", length = 100)
    private String displayName;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "guest")
    private boolean guest = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan")
    private UserPlan plan = UserPlan.FREE;

    @Column(name = "sessions_today")
    private short sessionsToday = 0;

    @Column(name = "sessions_reset_at")
    private LocalDateTime sessionsResetAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void normalizeData() {
        if (email != null) {
            email = email.toLowerCase();
        }

        if (sessionsResetAt == null) {
            sessionsResetAt = LocalDateTime.now();
        }
    }
}