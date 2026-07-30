package com.codecool.travelplanner.service;

import com.codecool.travelplanner.model.FlightOfferDto;

import java.util.List;

public interface FlightDataProvider {
    List<FlightOfferDto> getFlightOffers();
}
