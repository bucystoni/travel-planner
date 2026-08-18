package com.codecool.travelplanner.repository.flight;

import com.codecool.travelplanner.configuration.IgnavConfig;
import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.mapper.flight.FlightEntityMapper;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiFlightDataProvider implements FlightDataProvider {
    private final FlightEntityMapper entityMapper;
    private final IgnavConfig ignavConfig;
    private final RestClient restClient;

    public ApiFlightDataProvider(
            FlightEntityMapper entityMapper,
            IgnavConfig ignavConfig,
            RestClient restClient) {
        this.entityMapper = entityMapper;
        this.ignavConfig = ignavConfig;
        this.restClient = restClient;
    }

    @Override
    public List<FlightOfferEntity> getFlightOffers(String origin, String destination, LocalDate departureDate) {
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

        return entityMapper.toFlightOffers(response);
    }
}
