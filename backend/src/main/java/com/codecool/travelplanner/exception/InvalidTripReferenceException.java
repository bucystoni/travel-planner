package com.codecool.travelplanner.exception;

public class InvalidTripReferenceException extends RuntimeException {
    public InvalidTripReferenceException(Long id) {
        super("Unknown flight offer id: " + id);
    }
}
