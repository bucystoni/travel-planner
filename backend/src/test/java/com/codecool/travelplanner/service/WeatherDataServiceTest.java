package com.codecool.travelplanner.service;

import com.codecool.travelplanner.configuration.OpenWeatherConfiguration;
import com.codecool.travelplanner.dto.weather.WeatherApiResponse;
import com.codecool.travelplanner.service.weather.WeatherDataService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class WeatherDataServiceTest {

    // The answer we pretend OpenWeather sends back.
    private static final String FORECAST_JSON = """
    {
      "list": [
        {
          "dt_txt": "2026-08-04 12:00:00",
          "main": { "temp": 21.5, "temp_min": 19.8, "temp_max": 23.1 },
          "weather": [ { "description": "clear sky" } ]
        }
      ]
    }
    """;

    @Test
    void getForecastReadsTemperatureAndDescription() {
        // A RestClient whose requests are caught by the mock server instead of the network.
        RestClient.Builder builder = RestClient.builder();
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();

        OpenWeatherConfiguration config =
                new OpenWeatherConfiguration("https://weather.test", "test-key");
        WeatherDataService service = new WeatherDataService(builder.build(), config);

        // This is also a test of the URL the service builds.
        server.expect(requestTo("https://weather.test/data/2.5/forecast"
                        + "?lat=47.4979&lon=19.0402&appid=test-key&units=metric"))
                .andRespond(withSuccess(FORECAST_JSON, MediaType.APPLICATION_JSON));

        WeatherApiResponse response = service.getForecast(47.4979, 19.0402);

        assertThat(response.forecasts()).hasSize(1);
        assertThat(response.forecasts().getFirst().main().tempMin()).isEqualTo(19.8);
        assertThat(response.forecasts().getFirst().main().tempMax()).isEqualTo(23.1);
        assertThat(response.forecasts().getFirst().main().temp()).isEqualTo(21.5);
        assertThat(response.forecasts().getFirst().weather().getFirst().description())
                .isEqualTo("clear sky");
        server.verify(); // the request really happened
    }
}