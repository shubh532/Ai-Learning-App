package com.elearning.AILearning.controller;

import com.elearning.AILearning.dto.request.LoginRequest;
import com.elearning.AILearning.dto.request.RegisterRequest;
import com.elearning.AILearning.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of("message", "Welcome, you are authenticated...!"));
    }

    /**
     * POST /auth/register
     * Registers a new user and automatically attaches an HttpOnly auth cookie to the response.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @Valid @RequestBody RegisterRequest request,
            HttpServletResponse response) {

        log.info("Processing registration request for email: {}", request.getEmail());

        // Pass the response object down so the service can attach the cookie instantly
        authService.register(request, response);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User registered and logged in successfully"
        ));
    }

    /**
     * POST /auth/login
     * Validates credentials and attaches the HttpOnly auth cookie upon success.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response) {

        log.info("Processing login verification request for email: {}", request.getEmail());

        authService.login(request, response);

        return ResponseEntity.ok(Map.of(
                "message", "Login successful"
        ));
    }
}