package com.codecool.travelplanner.dto.flight;

import java.util.List;

public record FlightOfferDto(
        double price,
        String currency,
        String cabinClass,
        boolean requiresSelfTransfer,
        int totalDurationMinutes,
        List<FlightSegmentDto> segments) {
}
