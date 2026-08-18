package com.codecool.travelplanner.repository.flight;

import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;

import java.time.LocalDate;
import java.util.List;

public interface FlightDataProvider {
    List<FlightOfferEntity> getFlightOffers(
            String origin,
            String destination,
            LocalDate departureDate);
}
