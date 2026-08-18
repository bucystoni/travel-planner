package com.codecool.travelplanner.repository.flight;

import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Primary
public class SqlFlightDataProvider implements FlightDataProvider {
    private final FlightOfferRepository flightOfferRepository;

    public SqlFlightDataProvider(FlightOfferRepository flightOfferRepository) {
        this.flightOfferRepository = flightOfferRepository;
    }


    @Override
    public List<FlightOfferEntity> getFlightOffers(String origin, String destination, LocalDate departureDate) {
        return flightOfferRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
    }
}
