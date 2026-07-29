package com.codecool.travelplanner.mapper;

import com.codecool.travelplanner.dto.flight.FlightOfferDto;
import com.codecool.travelplanner.dto.flight.FlightSegmentDto;
import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.dto.ignav.ItineraryDto;
import com.codecool.travelplanner.dto.ignav.SegmentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightMapper {

    public List<FlightOfferDto> toFlightOffers(FlightResponseDto response) {

        return response.itineraries()
                .stream()
                .map(this::toFlightOffer)
                .toList();
    }

    private FlightOfferDto toFlightOffer(ItineraryDto itinerary) {
        List<FlightSegmentDto> segments = itinerary.outbound()
                .segments()
                .stream()
                .map(this::toFlightSegment)
                .toList();

        return new FlightOfferDto(
                itinerary.price().amount(),
                itinerary.price().currency(),
                itinerary.cabinClass(),
                itinerary.requiresSelfTransfer(),
                itinerary.outbound().durationMinutes(),
                segments);
    }

    private FlightSegmentDto toFlightSegment(SegmentDto segment) {
        return new FlightSegmentDto(
                segment.operatingCarrierName(),
                segment.flightNumber(),
                segment.aircraft(),
                segment.departureAirport(),
                segment.arrivalAirport(),
                segment.departureTimeUtc(),
                segment.arrivalTimeUtc(),
                segment.durationMinutes()
        );
    }
}
