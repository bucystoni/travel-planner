package com.codecool.travelplanner.repository.flight;

import com.codecool.travelplanner.configuration.IgnavConfig;
import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.exception.FlightApiException;
import com.codecool.travelplanner.mapper.flight.FlightMapper;
import com.codecool.travelplanner.model.FlightOfferDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiFlightDataProvider implements FlightDataProvider {
    private final FlightMapper flightMapper;
    private final IgnavConfig ignavConfig;
    private final RestClient restClient;

    public ApiFlightDataProvider(
            FlightMapper flightMapper,
            IgnavConfig ignavConfig,
            RestClient restClient) {
        this.flightMapper = flightMapper;
        this.ignavConfig = ignavConfig;
        this.restClient = restClient;
    }

    @Override
    public List<FlightOfferDto> getFlightOffers(String origin, String destination, LocalDate departureDate) {
        String url = ignavConfig.getBaseUrl() + "/fares/one-way";

        String body = """
        {
          "origin": "%s",
          "destination": "%s",
          "departure_date": "%s"
        }
        """.formatted(origin, destination, departureDate);

        try {
            FlightResponseDto response = restClient.post()
                    .uri(url)
                    .header("X-Api-Key", ignavConfig.getApiKey())
                    .header("Content-Type", "application/json")
                    .body(body)
                    .retrieve()
                    .body(FlightResponseDto.class);

            return flightMapper.toFlightOffers(response);
        } catch (RestClientException e) {
            throw new FlightApiException("Flight API encountered an issue", e);
        }
    }
}
