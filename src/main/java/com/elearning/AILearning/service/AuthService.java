package com.elearning.AILearning.service;

import com.elearning.AILearning.dto.request.RegisterRequest;
import com.elearning.AILearning.entity.User;
import com.elearning.AILearning.exception.UserAlreadyExistsException;
import com.elearning.AILearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

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

}
