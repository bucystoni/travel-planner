package com.codecool.travelplanner.exception;

public class FlightApiException extends RuntimeException {
    public FlightApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
