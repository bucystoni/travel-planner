package com.codecool.travelplanner.dto.flight;

import java.time.OffsetDateTime;

public record FlightSegmentDto(
        String carrier,
        String flightNumber,
        String aircraft,
        String departureAirport,
        String arrivalAirport,
        OffsetDateTime departureTime,
        OffsetDateTime arrivalTime,
        int durationMinutes) {

}
