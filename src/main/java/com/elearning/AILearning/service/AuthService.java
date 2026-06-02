package com.elearning.AILearning.service;

import com.elearning.AILearning.dto.request.LoginRequest;
import com.elearning.AILearning.dto.request.RegisterRequest;
import com.elearning.AILearning.entity.User;
import com.elearning.AILearning.exception.UserAlreadyExistsException;
import com.elearning.AILearning.repository.UserRepository;
import com.elearning.AILearning.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public User getUser(UUID userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void register(RegisterRequest request, HttpServletResponse response) {
        userRepo.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException("Email already registered");
        });

        User user = User.builder()
                .email(request.getEmail())
                .displayName(request.getDisplayName())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .authProvider(request.getAuthProvider())
                .guest(request.getGuest())
                .plan(request.getPlan())
                .build();

        userRepo.save(user);

        // Auto-login user upon successful account registration
        issueAuthCookie(user.getEmail(), response);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequest request, HttpServletResponse response) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        issueAuthCookie(user.getEmail(), response);
    }

    private void issueAuthCookie(String email, HttpServletResponse response) {
        String token = jwtUtil.generateToken(email);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Keep false for localhost HTTP testing; flip to true in production HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 1-day retention boundary

        response.addCookie(cookie);
    }
}