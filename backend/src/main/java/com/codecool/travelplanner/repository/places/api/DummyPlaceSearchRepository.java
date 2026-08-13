package com.codecool.travelplanner.repository.places.api;

import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;


public class DummyPlaceSearchRepository implements PlacesSearchRepository {

    private final ObjectMapper mapper = new ObjectMapper();

    private String buildFilePath(String cityName, String type) {
        return "mock-data/" + type + "-" + cityName.toLowerCase() + ".json";
    }

    private String buildFilePathForCity(String cityName) {
        return "mock-data/" + cityName.toLowerCase() + ".json";
    }

    @Override
    public GoogleCityResponseDto searchCity(String cityName) { // TODO: city not found exception
        try {
            String path = buildFilePathForCity(cityName);
            ClassPathResource resource = new ClassPathResource(path);
            GoogleCityResponseDto response = mapper.readValue(resource.getInputStream(), GoogleCityResponseDto.class);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GooglePoiResponseDto searchPoi(City city, String type) { // TODO: wrong type exception
        try {
            String path = buildFilePath(city.getName(), type);
            ClassPathResource resource = new ClassPathResource(path);
            GooglePoiResponseDto response = mapper.readValue(resource.getInputStream(), GooglePoiResponseDto.class);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GooglePoiResponseDto searchRestaurants(City city) {
        return searchPoi(city, "restaurants");
    }

    @Override
    public GooglePoiResponseDto searchAccomodations(City city) {
        return searchPoi(city, "hotels");
    }

    @Override
    public GooglePoiResponseDto searchSights(City city) {
        return searchPoi(city, "sights");
    }
}
