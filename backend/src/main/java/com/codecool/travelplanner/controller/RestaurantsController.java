package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.RestaurantsApi;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.service.places.PlacesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RestaurantsController implements RestaurantsApi {

    private final PlacesService placesDataService;

    public RestaurantsController(PlacesService placesDataService) {
        this.placesDataService = placesDataService;
    }

    @Override
    public ResponseEntity<PointOfInterestDto> restaurantsGet(String destinationName) {

        PointOfInterestDto restaurants = placesDataService.getRestaurants(destinationName);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}
