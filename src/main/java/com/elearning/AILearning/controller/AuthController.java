package com.elearning.AILearning.controller;

import com.elearning.AILearning.dto.request.RegisterRequest;
import com.elearning.AILearning.entity.User;
import com.elearning.AILearning.service.AuthService;
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
        System.out.println("Request Accepted");
        System.out.println(user.toString());
        authService.register(user);
        return ResponseEntity.ok("Request Completed");

    }
}
