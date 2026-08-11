package com.codecool.travelplanner.service;

import com.codecool.travelplanner.configuration.IgnavConfig;
import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.mapper.FlightMapper;
import com.codecool.travelplanner.model.FlightOfferDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiFlightDataService implements FlightDataProvider {

    private final RestClient restClient;
    private final IgnavConfig ignavConfig;
    private final FlightMapper flightMapper;

    public ApiFlightDataService(RestClient restClient, IgnavConfig ignavConfig, FlightMapper flightMapper) {
        this.restClient = restClient;
        this.ignavConfig = ignavConfig;
        this.flightMapper = flightMapper;
    }

    @Override
    public List<FlightOfferDto> getFlightOffers(String origin,
                                                String destination,
                                                LocalDate departureDate) {

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

        return flightMapper.toFlightOffers(response);
    }
}
