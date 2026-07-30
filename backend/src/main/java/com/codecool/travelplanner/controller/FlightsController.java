package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.FlightsApi;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.service.ApiFlightDataService;
import com.codecool.travelplanner.service.FlightDataProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightsController implements FlightsApi {
    private final FlightDataProvider flightDataProvider;

    public FlightsController(FlightDataProvider flightDataProvider) {
        this.flightDataProvider = flightDataProvider;
    }

    @Override
    public ResponseEntity<List<FlightOfferDto>> flightsGet(String destinationIataCode, String departureIataCode, LocalDate date) {
        List<FlightOfferDto> tickets = flightDataProvider.getFlightOffers();
        return ResponseEntity.ok(tickets);
    }
}
