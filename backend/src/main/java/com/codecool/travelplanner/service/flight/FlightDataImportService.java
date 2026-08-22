package com.codecool.travelplanner.service.flight;

import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.mapper.flight.FlightEntityMapper;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.repository.flight.FlightOfferRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightDataImportService {
    private final ObjectMapper objectMapper;
    private final FlightEntityMapper flightEntityMapper;
    private final FlightOfferRepository repository;

    public FlightDataImportService(
            ObjectMapper objectMapper,
            FlightEntityMapper flightEntityMapper,
            FlightOfferRepository repository) {
        this.objectMapper = objectMapper;
        this.flightEntityMapper = flightEntityMapper;
        this.repository = repository;
    }

    public void importFlightData() {
        List<FlightResponseDto> responses = getFlightOffers();

        List<FlightOfferEntity> allFlights = responses.stream()
                .flatMap(response -> flightEntityMapper.toFlightOffers(response).stream())
                .toList();

        repository.saveAll(allFlights);
    }

    public List<FlightResponseDto> getFlightOffers() {
        List<FlightResponseDto> testFlights = new ArrayList<>();
        try {
            ClassPathResource resource1 =
                    new ClassPathResource("mock-data/ignav_response_BUD_to_LON_2026-08-27.json");

            FlightResponseDto response1 = objectMapper.readValue(
                    resource1.getInputStream(),
                    FlightResponseDto.class);
            testFlights.add(response1);


            ClassPathResource resource2 =
                    new ClassPathResource("mock-data/ignav_response_BUD_to_NRT_2026-09-15.json");

            FlightResponseDto response2 = objectMapper.readValue(
                    resource2.getInputStream(),
                    FlightResponseDto.class);
            testFlights.add(response2);

            return testFlights;

        } catch (IOException e) {
            throw new RuntimeException("Failed to load mock flight data", e);
        }
    }

    public boolean isDatabaseInitialized() {
        return repository.count() > 0;
    }

}
