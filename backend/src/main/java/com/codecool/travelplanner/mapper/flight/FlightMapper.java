package com.codecool.travelplanner.mapper.flight;

import com.codecool.travelplanner.dto.flight.FlightSegmentDto;
import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.dto.ignav.ItineraryDto;
import com.codecool.travelplanner.dto.ignav.SegmentDto;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.model.entity.flight.FlightSegmentEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FlightMapper {

    // ==================== IGNAV API --> FlightOfferDto ==================== //

    public List<FlightOfferDto> toFlightOffers(FlightResponseDto response) {
        LocalDate departureDate = LocalDate.parse(response.departureDate());

        return response.itineraries()
                .stream()
                .map(itinerary -> toFlightOffer(
                        itinerary,
                        response.origin(),
                        response.destination(),
                        departureDate))
                .toList();
    }

    private FlightOfferDto toFlightOffer(
            ItineraryDto itinerary,
            String origin,
            String destination,
            LocalDate departureDate) {
        List<FlightSegmentDto> segments = itinerary.outbound()
                .segments()
                .stream()
                .map(this::toFlightSegment)
                .toList();

        return new FlightOfferDto()
                .ignavId(itinerary.ignavId())
                .origin(origin)
                .destination(destination)
                .departureDate(departureDate)
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

    public FlightOfferDto toFlightOffer(FlightOfferEntity entity) {
        List<FlightSegmentDto> segments = entity.getSegments()
                .stream()
                .map(this::toFlightSegment)
                .toList();

        return new FlightOfferDto()
                .ignavId(entity.getId())
                .origin(entity.getOrigin())
                .destination(entity.getDestination())
                .departureDate(entity.getDepartureDate())
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
