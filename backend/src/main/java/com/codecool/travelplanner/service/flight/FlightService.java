package com.codecool.travelplanner.service.flight;

import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.repository.flight.FlightDataProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    private final FlightDataProvider flightDataProvider;


    public FlightService(FlightDataProvider flightDataProvider) {
        this.flightDataProvider = flightDataProvider;

    }

    public List<FlightOfferDto> getFlightOffers(String origin, String destination, LocalDate date) {
        return flightDataProvider.getFlightOffers(origin, destination, date);
    }

}
