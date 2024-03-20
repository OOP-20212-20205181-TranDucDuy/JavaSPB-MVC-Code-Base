package com.example.javacodebase.service;

import com.example.javacodebase.exception.BadRequestException;
import com.example.javacodebase.model.User;
import com.example.javacodebase.model.enums.AuthProvider;
import com.example.javacodebase.payload.ApiResponse;
import com.example.javacodebase.payload.SignUpRequest;
import com.example.javacodebase.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<ApiResponse> saveUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "User registered successfully"));
    }
}
