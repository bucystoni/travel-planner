package com.codecool.travelplanner.service;

import com.codecool.travelplanner.dto.WeatherApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherDataServiceTest {

    @Autowired
    private WeatherDataService weatherDataService;

    @Test
    void testGetForecast() {
        // Budapest
        double lat = 47.4979;
        double lon = 19.0402;

        WeatherApiResponse response = weatherDataService.getForecast(lat, lon);

       // System.out.println(response.getList().size());
        System.out.println("Temperature:" + response.getList().get(0).getMain().getTemp());
        System.out.println( "Weather description:" + response.getList().get(0).getWeather().get(0).getDescription());
    }
}