package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.SightsApi;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.service.places.PlacesDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SightsController implements SightsApi {

    private final PlacesDataService placesDataService;

    public SightsController(PlacesDataService placesDataService) {
        this.placesDataService = placesDataService;

    }

    @Override
    public ResponseEntity<PointOfInterestDto> sightsGet(String destinationName) {
        return null;
    }
}
