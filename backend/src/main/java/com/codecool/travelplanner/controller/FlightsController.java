package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.FlightsApi;
import com.codecool.travelplanner.model.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class FlightsController implements FlightsApi {

    @Override
    public ResponseEntity<List<Flight>> flightsGet(String destinationIataCode, String departureIataCode, LocalDate date) {
        Flight flight1 = new Flight();
        flight1.setPriceAmount(89.99);
        flight1.setPriceCurrency("EUR");
        flight1.setCarrier("Wizz Air");
        flight1.setAirCraft("Airbus A321");
        flight1.setFlightNumber("W6 1234");
        flight1.setDepartureIataCode("BUD");
        flight1.setDestinationIataCode("CDG");
        flight1.setDurationMinutes(150);
        flight1.setCabinClass("Economy");
        flight1.setRequiredSelfTransfer(false);
        flight1.setDepartureTime(OffsetDateTime.of(LocalDateTime.of(2026, 8, 15, 6, 30), ZoneOffset.UTC));
        flight1.setArrivalTime(OffsetDateTime.of(LocalDateTime.of(2026, 8, 15, 9, 0), ZoneOffset.UTC));

        Flight flight2 = new Flight();
        flight2.setPriceAmount(134.50);
        flight2.setPriceCurrency("EUR");
        flight2.setCarrier("Lufthansa");
        flight2.setAirCraft("Boeing 737");
        flight2.setFlightNumber("LH 5678");
        flight2.setDepartureIataCode("BUD");
        flight2.setDestinationIataCode("CDG");
        flight2.setDurationMinutes(165);
        flight2.setCabinClass("Business");
        flight2.setRequiredSelfTransfer(true);
        flight2.setDepartureTime(OffsetDateTime.of(LocalDateTime.of(2026, 8, 15, 14, 0), ZoneOffset.UTC));
        flight2.setArrivalTime(OffsetDateTime.of(LocalDateTime.of(2026, 8, 15, 16, 45), ZoneOffset.UTC));

        List<Flight> dummyFlights = List.of(flight1, flight2);

        return new ResponseEntity<>(dummyFlights, HttpStatus.OK);
    }
}
