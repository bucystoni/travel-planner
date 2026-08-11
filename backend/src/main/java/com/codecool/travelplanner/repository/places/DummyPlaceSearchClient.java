package com.codecool.travelplanner.googleplaces;

import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.POI;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class DummyPlaceSearchClient implements PlacesSearchClient {

    private final ObjectMapper mapper = new ObjectMapper();

    private String buildFilePath(String cityName, String type) {
        return "mock-data/" + cityName.toLowerCase() + "-" + type + ".json";
    }

    private String buildFilePathForCity(String cityName) {
        return "mock-data/" + cityName.toLowerCase() + ".json";
    }

    @Override
    public City searchCity(String search) { // TODO: city not found exception
        try {
            String path = buildFilePathForCity(search);
            ClassPathResource resource = new ClassPathResource(path);
            GoogleCityResponseDto response = mapper.readValue(resource.getInputStream(), GoogleCityResponseDto.class);
            return GooglePlaceMapper.convertResponseToCityList(response).getFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<POI> searchPoi(City city, String type) { // TODO: wrong type exception
        try {
            String path = buildFilePath(city.toString(), type);
            ClassPathResource resource = new ClassPathResource(path);
            GooglePoiResponseDto response = mapper.readValue(resource.getInputStream(), GooglePoiResponseDto.class);
            return GooglePlaceMapper.convertResponseToPoiList(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<POI> searchRestaurants(City city) {
        return searchPoi(city, "restaurants");
    }

    @Override
    public List<POI> searchHotels(City city) {
        return searchPoi(city, "hotels");
    }

    @Override
    public List<POI> searchSights(City city) {
        return searchPoi(city, "sights");
    }
}
