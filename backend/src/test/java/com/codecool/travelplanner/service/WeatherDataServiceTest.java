package com.codecool.travelplanner.service;

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

        String response = weatherDataService.getForecast(lat, lon);

        System.out.println(response);
    }
}
