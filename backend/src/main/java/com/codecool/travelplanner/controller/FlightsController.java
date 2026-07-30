package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.FlightsApi;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.service.FlightDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightsController implements FlightsApi {
    private final FlightDataService flightDataService;

    public FlightsController(FlightDataService flightDataService) {
        this.flightDataService = flightDataService;
    }

    @Override
    public ResponseEntity<List<FlightOfferDto>> flightsGet(String destinationIataCode, String departureIataCode, LocalDate date) {
        List<FlightOfferDto> tickets = flightDataService.getFlightData();
        return ResponseEntity.ok(tickets);
    }
}
