package com.codecool.travelplanner.repository.flight;

import com.codecool.travelplanner.mapper.flight.FlightMapper;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Primary
public class SqlFlightDataProvider implements FlightDataProvider {
    private final FlightOfferRepository flightOfferRepository;
    private final FlightMapper flightMapper;

    public SqlFlightDataProvider(
            FlightOfferRepository flightOfferRepository,
            FlightMapper flightMapper) {
        this.flightOfferRepository = flightOfferRepository;
        this.flightMapper = flightMapper;
    }


    @Override
    public List<FlightOfferDto> getFlightOffers(String origin, String destination, LocalDate departureDate) {
        List<FlightOfferEntity> entities = flightOfferRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
        return flightMapper.toFlightOffers(entities);
    }
}
