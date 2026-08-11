package com.codecool.travelplanner.repository.flight;

import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightOfferRepository extends JpaRepository<FlightOfferEntity, Long> {
    List<FlightOfferEntity> findByOriginAndDestinationAndDepartureDate(
            String origin,
            String destination,
            LocalDate departureDate);
}
