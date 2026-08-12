package com.codecool.travelplanner.service.places;

import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.model.PointOfInterest;
import com.codecool.travelplanner.repository.places.PlacesSearchClient;

public class PlacesDataService {
    private final PlacesSearchClient placesSearchClient;

    public PlacesDataService(PlacesSearchClient placesSearchClient) {
        this.placesSearchClient = placesSearchClient;
    }

    public PointOfInterest searchCity(GoogleCityResponseDto response) {
        return null;
    };
}
