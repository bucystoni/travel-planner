package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.AuthApi;
import com.codecool.travelplanner.model.AuthResponse;
import com.codecool.travelplanner.model.LoginRequest;
import com.codecool.travelplanner.model.playload.UserRequest;
import com.codecool.travelplanner.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> authRegisterPost(@RequestBody UserRequest request) {
        userService.registerUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }






    @Override
    public ResponseEntity<AuthResponse> authLoginPost(LoginRequest request) {
        AuthResponse dummyResponse = new AuthResponse();
        dummyResponse.setToken("dummy-jwt-token-1234567890");

        return new ResponseEntity<>(dummyResponse, HttpStatus.OK);
    }
}
