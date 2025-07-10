package com.example.dogsapi.controller;

import com.example.dogsapi.model.User;
import com.example.dogsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"https://teste-oba.vercel.app", "https://obafloripa.vercel.app", "http://localhost:5501"})
public class UserController {
    @Autowired
    private UserService userService;

    // POST /api/users - Create new user (SUPER_ADMIN only)
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest req) {
        try {
            User created = userService.createUser(req.username, req.password, req.role);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(created.getUsername(), created.getRole()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /api/users - List all users
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers().stream()
            .map(u -> new UserResponse(u.getUsername(), u.getRole()))
            .toList();
        return ResponseEntity.ok(users);
    }

    // GET /api/users/{username} - Get user by username
    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user
            .map(u -> ResponseEntity.ok(new UserResponse(u.getUsername(), u.getRole())))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DTO for user creation
    public static class CreateUserRequest {
        public String username;
        public String password;
        public String role; // ADMIN or SUPER_ADMIN
    }

    // DTO for user response (no password)
    public static class UserResponse {
        public String username;
        public String role;
        public UserResponse(String username, String role) {
            this.username = username;
            this.role = role;
        }
    }
} 