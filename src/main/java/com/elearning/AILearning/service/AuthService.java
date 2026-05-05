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
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

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
    }

    public String login(LoginRequest request, HttpServletResponse response) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);

        return "Login Successful";
    }
}