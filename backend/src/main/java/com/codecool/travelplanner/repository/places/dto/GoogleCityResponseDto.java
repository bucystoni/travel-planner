package com.codecool.travelplanner.repository.places.dto;

import java.util.List;

public class GoogleCityResponseDto {
    private List<GoogleCityDto> places;

    public List<GoogleCityDto> getPlaces() {
        return places;
    }
}