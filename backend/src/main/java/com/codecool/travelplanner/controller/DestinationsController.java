package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.DestinationsApi;
import com.codecool.travelplanner.model.FlightOfferDto;

import com.codecool.travelplanner.service.FlightDataProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DestinationsController implements DestinationsApi {

    private final FlightDataProvider flightDataProvider;

    public DestinationsController(FlightDataProvider flightDataProvider) {
        this.flightDataProvider = flightDataProvider;
    }

    @Override
    public ResponseEntity<List<FlightOfferDto>> destinationsGet(String destinationIataCode, String departureIataCode, LocalDate date) {
        List<FlightOfferDto> tickets = flightDataProvider.getFlightOffers();
        return ResponseEntity.ok(tickets);
    }
}