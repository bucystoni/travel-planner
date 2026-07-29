package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.AuthApi;
import com.codecool.travelplanner.model.AuthResponse;
import com.codecool.travelplanner.model.LoginRequest;
import com.codecool.travelplanner.model.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    @Override
    public ResponseEntity<AuthResponse> authLoginPost(LoginRequest request) {
        AuthResponse dummyResponse = new AuthResponse();
        dummyResponse.setToken("dummy-jwt-token-1234567890");

        return new ResponseEntity<>(dummyResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> authRegisterPost(RegisterRequest request) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
