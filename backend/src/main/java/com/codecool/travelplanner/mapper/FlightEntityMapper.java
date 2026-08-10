package com.codecool.travelplanner.mapper;

import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.dto.ignav.ItineraryDto;
import com.codecool.travelplanner.dto.ignav.SegmentDto;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.model.entity.flight.FlightSegmentEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FlightEntityMapper {


    public List<FlightOfferEntity> toFlightOffers(FlightResponseDto response) {
        String origin = response.origin();
        String destination = response.destination();
        LocalDate departureDate = LocalDate.parse(response.departureDate());

        return response.itineraries().stream()
                .map(itinerary -> toFlightOffer(itinerary, origin, destination, departureDate))
                .toList();
    }

    private FlightOfferEntity toFlightOffer(
            ItineraryDto itinerary,
            String origin,
            String destination,
            LocalDate departureDate) {

        FlightOfferEntity entity = new FlightOfferEntity(
                origin,
                destination,
                departureDate,
                itinerary.price().amount(),
                itinerary.price().currency(),
                itinerary.cabinClass(),
                itinerary.requiresSelfTransfer(),
                itinerary.outbound().durationMinutes());

        for (SegmentDto segmentDto : itinerary.outbound().segments()) {
            entity.addSegment(toFlightSegment(segmentDto));
        }

        return entity;
    }

    private FlightSegmentEntity toFlightSegment(SegmentDto segment) {
        return new FlightSegmentEntity(
                segment.operatingCarrierName(),
                segment.flightNumber(),
                segment.aircraft(),
                segment.departureAirport(),
                segment.arrivalAirport(),
                segment.departureTimeUtc(),
                segment.arrivalTimeUtc(),
                segment.durationMinutes());
    }
}
