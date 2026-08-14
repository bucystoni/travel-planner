package com.codecool.travelplanner.exception;

public class CityNotFoundException extends PlacesException {
    public CityNotFoundException (String cityName) {
        super("City not found: " + cityName);
    }
}
