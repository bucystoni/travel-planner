package com.codecool.travelplanner.service;

import com.codecool.travelplanner.configuration.IgnavConfig;
import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Service
public class FlightDataService {

    private final RestClient restClient;
    private final IgnavConfig ignavConfig;

    public FlightDataService(RestClient restClient, IgnavConfig ignavConfig) {
        this.restClient = restClient;
        this.ignavConfig = ignavConfig;
    }

    public FlightResponseDto getFlightData() {
        String origin = "BUD";
        String destination = "NRT";
        LocalDate departureDate = LocalDate.of(2026, 9, 15);
        String url = ignavConfig.getBaseUrl() + "/fares/one-way";

        String body = """
        {
          "origin": "%s",
          "destination": "%s",
          "departure_date": "%s"
        }
        """.formatted(origin, destination, departureDate);

        FlightResponseDto response = restClient.post()
                .uri(url)
                .header("X-Api-Key", ignavConfig.getApiKey())
                .header("Content-Type", "application/json")
                .body(body)
                .retrieve()
                .body(FlightResponseDto.class);
        return response;
    }
}
