package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.FlightsApi;
import com.codecool.travelplanner.model.FlightOfferDto;

import service.flight.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightsController implements FlightsApi {
    private final FlightService flightService;

    public FlightsController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public ResponseEntity<List<FlightOfferDto>> flightsGet(String destinationIataCode, String departureIataCode, LocalDate date) {
        List<FlightOfferDto> tickets = flightService.getFlightOffers(
                departureIataCode,
                destinationIataCode,
                date);
        return ResponseEntity.ok(tickets);
    }
}
