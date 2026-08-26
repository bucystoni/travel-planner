package com.codecool.travelplanner.controller.advice;

import com.codecool.travelplanner.exception.InvalidCredentialsException;
import com.codecool.travelplanner.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalidCredentialsExceptionHandler(InvalidCredentialsException ex) {
        return ex.getMessage();
    }
}
