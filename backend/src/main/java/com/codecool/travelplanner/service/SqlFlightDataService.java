package com.codecool.travelplanner.service;

import com.codecool.travelplanner.model.FlightOfferDto;

import java.time.LocalDate;
import java.util.List;

public class SqlFlightDataService implements FlightDataProvider{
    @Override
    public List<FlightOfferDto> getFlightOffers(String origin,
                                                String destination,
                                                LocalDate departureDate) {
        return List.of();
    }
}
