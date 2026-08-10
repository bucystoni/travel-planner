package com.codecool.travelplanner.service;

import com.codecool.travelplanner.model.FlightOfferDto;

import java.util.List;

public class SqlFlightDataService implements FlightDataProvider{
    @Override
    public List<FlightOfferDto> getFlightOffers() {
        return List.of();
    }
}
