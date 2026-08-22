package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.SightsApi;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.service.places.PlacesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SightsController implements SightsApi {

    private final PlacesService placesDataService;

    public SightsController(PlacesService placesDataService) {
        this.placesDataService = placesDataService;

    }

    @Override
    public ResponseEntity<PointOfInterestDto> sightsGet(String destinationName) {
        PointOfInterestDto sights = placesDataService.getSights(destinationName);
        return new ResponseEntity<>(sights, HttpStatus.OK);
    }
}
