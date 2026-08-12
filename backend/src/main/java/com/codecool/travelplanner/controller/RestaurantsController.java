package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.RestaurantsApi;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.service.places.PlacesDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestaurantsController implements RestaurantsApi {

    private final PlacesDataService placesDataService;

    public RestaurantsController(PlacesDataService placesDataService) {
        this.placesDataService = placesDataService;
    }

    @Override
    public ResponseEntity<PointOfInterestDto> restaurantsGet(String destinationName) {

        return null;
    }
}
