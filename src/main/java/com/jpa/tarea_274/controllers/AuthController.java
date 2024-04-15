package com.jpa.tarea_274.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.tarea_274.dto.requests.LoginRequest;
import com.jpa.tarea_274.dto.requests.RegisterRequest;
import com.jpa.tarea_274.dto.responses.AuthResponse;
import com.jpa.tarea_274.dto.responses.UserResponse;
import com.jpa.tarea_274.models.User;
import com.jpa.tarea_274.services.impl.AuthServiceImp;
import com.jpa.tarea_274.services.impl.UserServiceImp;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthServiceImp authService;

    @Autowired
    private UserServiceImp userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok(Map.of("message", "!Usuario Registrado!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUserByUsername() {
        return ResponseEntity.ok(userService.getUserByUserName());
    }

}