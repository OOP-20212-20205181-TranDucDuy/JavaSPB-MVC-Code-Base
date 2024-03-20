package com.example.javacodebase.controller;

import com.example.javacodebase.exception.BadRequestException;
import com.example.javacodebase.model.enums.AuthProvider;
import com.example.javacodebase.model.User;
import com.example.javacodebase.payload.ApiResponse;
import com.example.javacodebase.payload.AuthResponse;
import com.example.javacodebase.payload.LoginRequest;
import com.example.javacodebase.payload.SignUpRequest;
import com.example.javacodebase.repository.UserRepository;
import com.example.javacodebase.security.TokenProvider;
import com.example.javacodebase.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userService.saveUser(signUpRequest);
    }

}
