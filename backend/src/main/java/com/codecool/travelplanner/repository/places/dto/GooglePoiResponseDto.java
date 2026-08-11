package com.codecool.travelplanner.repository.places.dto;

import java.util.List;

public class GooglePoiResponseDto {
    private List<GooglePoiDto> places;

    public List<GooglePoiDto> getPlaces() {
        return places;
    }
}