package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.AuthApi;
import com.codecool.travelplanner.model.AuthResponse;
import com.codecool.travelplanner.model.LoginRequest;
import com.codecool.travelplanner.model.RegisterRequest;
import com.codecool.travelplanner.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<Void> registerUser(@RequestBody RegisterRequest request) {
        authService.registerUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
