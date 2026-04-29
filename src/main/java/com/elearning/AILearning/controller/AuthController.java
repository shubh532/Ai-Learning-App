package com.elearning.AILearning.controller;

import com.elearning.AILearning.dto.request.LoginRequest;
import com.elearning.AILearning.dto.request.RegisterRequest;
import com.elearning.AILearning.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public String home() {
        return "Welcome, you are authenticated...!";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest user) {
        authService.register(user);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest user,
            HttpServletResponse response) {

        String result = authService.login(user, response);

        return ResponseEntity.ok(result);
    }
}