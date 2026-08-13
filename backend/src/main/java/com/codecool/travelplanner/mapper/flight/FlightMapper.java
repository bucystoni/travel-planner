package com.codecool.travelplanner.mapper.flight;

import com.codecool.travelplanner.dto.flight.FlightSegmentDto;
import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.dto.ignav.ItineraryDto;
import com.codecool.travelplanner.dto.ignav.SegmentDto;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.model.entity.flight.FlightSegmentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightMapper {

    // ==================== IGNAV API --> FlightOfferDto ==================== //

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

        return new FlightOfferDto()
                .price(itinerary.price().amount())
                .currency(itinerary.price().currency())
                .cabinClass(itinerary.cabinClass())
                .requiresSelfTransfer(itinerary.requiresSelfTransfer())
                .totalDurationMinutes(itinerary.outbound().durationMinutes())
                .segments(segments);
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

    // ==================== SQL Entity --> FlightOfferDto ==================== //

    public List<FlightOfferDto> toFlightOffers(List<FlightOfferEntity> entities) {
        return entities.stream()
                .map(this::toFlightOffer)
                .toList();
    }

    private FlightOfferDto toFlightOffer(FlightOfferEntity entity) {
        List<FlightSegmentDto> segments = entity.getSegments()
                .stream()
                .map(this::toFlightSegment)
                .toList();

        return new FlightOfferDto()
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .cabinClass(entity.getCabinClass())
                .requiresSelfTransfer(entity.isRequiresSelfTransfer())
                .totalDurationMinutes(entity.getTotalDurationMinutes())
                .segments(segments);
    }

    private FlightSegmentDto toFlightSegment(FlightSegmentEntity entity) {
        return new FlightSegmentDto(
                entity.getCarrier(),
                entity.getFlightNumber(),
                entity.getAircraft(),
                entity.getDepartureAirport(),
                entity.getArrivalAirport(),
                entity.getDepartureTime(),
                entity.getArrivalTime(),
                entity.getDurationMinutes()
        );
    }
}
