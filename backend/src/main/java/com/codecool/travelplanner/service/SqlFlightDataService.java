package com.codecool.travelplanner.service;

import com.codecool.travelplanner.mapper.FlightMapper;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.repository.flight.FlightOfferRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Primary
public class SqlFlightDataService implements FlightDataProvider{
    private final FlightOfferRepository repository;
    private final FlightMapper flightMapper;

    public SqlFlightDataService(FlightOfferRepository repository, FlightMapper flightMapper) {
        this.repository = repository;
        this.flightMapper = flightMapper;
    }


    @Override
    public List<FlightOfferDto> getFlightOffers(String origin,
                                                String destination,
                                                LocalDate departureDate) {
        List<FlightOfferEntity> entities =
                repository.findByOriginAndDestinationAndDepartureDate(
                        origin,
                        destination,
                        departureDate);
        return flightMapper.toFlightOffers(entities);
    }
}
