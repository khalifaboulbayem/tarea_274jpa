package com.jpa.tarea_274.services.impl;

import org.springframework.stereotype.Service;

import com.jpa.tarea_274.config.jwt.JwtService;
import com.jpa.tarea_274.dto.requests.LoginRequest;
import com.jpa.tarea_274.dto.requests.RegisterRequest;
import com.jpa.tarea_274.dto.responses.AuthResponse;
import com.jpa.tarea_274.emuns.Role;
import com.jpa.tarea_274.models.User;
import com.jpa.tarea_274.repositories.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails user = (UserDetails) userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no existe!"));
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("ERROR: " + e.getMessage());
        }

    }

    public User register(RegisterRequest request) {

        if (request.getUsername().length() == 0 || request.getUsername() == null) {
            throw new UsernameNotFoundException(request.getUsername() + " No es valido.");
        }

        if (request.getEmail().length() == 0 || request.getEmail() == null) {
            throw new UsernameNotFoundException(request.getUsername() + " no es valido.");
        }

        if (request.getPassword().length() == 0 || request.getPassword() == null) {
            throw new UsernameNotFoundException(request.getUsername() + " no es valido");
        }

        if (request.getUsername().length() == 0 || request.getUsername() == null) {
            throw new UsernameNotFoundException(request.getUsername() + " no es valido");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UsernameNotFoundException(request.getEmail() + " ya existe");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameNotFoundException(request.getUsername() + " ya existe");
        }
        if (!validateEmail(request.getEmail())) {
            throw new UsernameNotFoundException(request.getEmail() + " no es valido.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    private boolean validateEmail(String email) {
        String regexEmail = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
