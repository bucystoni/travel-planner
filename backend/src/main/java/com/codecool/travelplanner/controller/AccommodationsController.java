package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.AccommodationsApi;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.service.places.PlacesDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccommodationsController implements AccommodationsApi {

    private final PlacesDataService placesDataService;

    public AccommodationsController(PlacesDataService placesDataService) {
        this.placesDataService = placesDataService;
    }

    @Override
    public ResponseEntity<PointOfInterestDto> accommodationsGet(String destinationName) {

        PointOfInterestDto accommodations = placesDataService.getAccommodations(destinationName);
        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }
}
