package com.codecool.travelplanner.dto.ignav;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record FlightResponseDto(
        String origin,
        String destination,
        @JsonProperty("departure_date") String departureDate,
        List<ItineraryDto> itineraries) {
}
