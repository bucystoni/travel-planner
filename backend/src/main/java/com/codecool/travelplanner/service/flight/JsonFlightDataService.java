package com.codecool.travelplanner.service.flight;

import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.mapper.flight.FlightMapper;
import com.codecool.travelplanner.model.FlightOfferDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class JsonFlightDataService implements FlightDataProvider{

    private final ObjectMapper objectMapper;
    private final FlightMapper flightMapper;

    public JsonFlightDataService(
            ObjectMapper objectMapper,
            FlightMapper flightMapper) {
        this.objectMapper = objectMapper;
        this.flightMapper = flightMapper;
    }

    @Override
    public List<FlightOfferDto> getFlightOffers(String origin,
                                                String destination,
                                                LocalDate departureDate) {
        try {
            ClassPathResource resource =
                    new ClassPathResource("mock-data/ignav_response_BUD_to_LON_2026-08-27.json");

            FlightResponseDto response = objectMapper.readValue(
                    resource.getInputStream(),
                    FlightResponseDto.class
            );

            return flightMapper.toFlightOffers(response);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load mock flight data", e);
        }
    }
}
