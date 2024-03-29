package com.example.javacodebase.controller;

import com.example.javacodebase.exception.ResourceNotFoundException;
import com.example.javacodebase.model.User;
import com.example.javacodebase.repository.UserRepository;
import com.example.javacodebase.security.CurrentUser;
import com.example.javacodebase.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    @GetMapping("/user/test")
    public ResponseEntity<String> getCurrentUser() {
        return ResponseEntity.ok("Test User");
    }
}
