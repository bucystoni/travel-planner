package com.codecool.travelplanner.exception;

public abstract class PlacesException extends RuntimeException {
    public PlacesException(String message) {
        super(message);
    }
    public PlacesException(String message, Throwable cause) {
        super(message, cause);
    }
}