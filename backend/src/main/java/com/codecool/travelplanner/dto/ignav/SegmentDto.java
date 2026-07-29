package com.codecool.travelplanner.dto.ignav;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record SegmentDto(

        @JsonProperty("marketing_carrier_code")
        String marketingCarrierCode,

        @JsonProperty("flight_number")
        String flightNumber,

        @JsonProperty("operating_carrier_name")
        String operatingCarrierName,

        @JsonProperty("departure_airport")
        String departureAirport,

        @JsonProperty("departure_time_local")
        String departureTimeLocal,

        @JsonProperty("departure_timezone")
        String departureTimezone,

        @JsonProperty("departure_time_utc")
        OffsetDateTime departureTimeUtc,

        @JsonProperty("arrival_airport")
        String arrivalAirport,

        @JsonProperty("arrival_time_local")
        String arrivalTimeLocal,

        @JsonProperty("arrival_timezone")
        String arrivalTimezone,

        @JsonProperty("arrival_time_utc")
        OffsetDateTime arrivalTimeUtc,

        @JsonProperty("duration_minutes")
        int durationMinutes,

        String aircraft

) {
}