package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.DestinationsApi;
import com.codecool.travelplanner.mapper.places.PlacesMapper;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.FlightOfferDto;

import com.codecool.travelplanner.service.flight.FlightService;
import com.codecool.travelplanner.service.places.PlacesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DestinationsController implements DestinationsApi {

    private final PlacesService placesService;

    public DestinationsController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @Override
    public ResponseEntity<List<City>> destinationsGet(String cityName) {
        City city = PlacesMapper.convertResponseToCity(placesService.searchCity(cityName));
        return ResponseEntity.ok(List.of(city));
    }


}