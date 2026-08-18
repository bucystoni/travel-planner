package com.codecool.travelplanner.repository.flight;

import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FlightOfferRepository extends JpaRepository<FlightOfferEntity, Long> {
    @Query("""
       select distinct o from FlightOfferEntity o
       left join fetch o.segments
       where o.origin = :origin and o.destination = :destination
         and o.departureDate = :date
       """)
    List<FlightOfferEntity> findByOriginAndDestinationAndDepartureDate(
            String origin,
            String destination,
            LocalDate date);
}
