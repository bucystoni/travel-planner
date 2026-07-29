package com.codecool.travelplanner.service;

import com.codecool.travelplanner.configuration.OpenWeatherConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherDataService {
    private final RestClient restClient;
    private final OpenWeatherConfiguration openWeatherConfiguration;

    public WeatherDataService(RestClient restClient, OpenWeatherConfiguration openWeatherConfiguration) {
        this.restClient = restClient;
        this.openWeatherConfiguration = openWeatherConfiguration;
    }

    public String getForecast(double lat, double lon) {
        String url = openWeatherConfiguration.getBaseUrl()
                + "/data/2.5/forecast?lat=" + lat
                + "&lon=" + lon
                + "&appid=" + openWeatherConfiguration.getApiKey()
                + "&units=metric";

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }
}
