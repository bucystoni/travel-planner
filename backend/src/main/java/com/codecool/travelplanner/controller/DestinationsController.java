package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.DestinationsApi;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.service.FlightDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DestinationsController implements DestinationsApi {

    private final FlightDataService flightDataService;

    public DestinationsController(FlightDataService flightDataService) {
        this.flightDataService = flightDataService;
    }

    @Override
    public ResponseEntity<List<FlightOfferDto>> destinationsGet(String destinationIataCode, String departureIataCode, LocalDate date) {
        List<FlightOfferDto> tickets = flightDataService.getFlightData();
        return ResponseEntity.ok(tickets);
    }
}