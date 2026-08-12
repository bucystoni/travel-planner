package com.codecool.travelplanner.repository.places;

import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.POI;

import java.util.List;

public interface PlacesSearchClient {
    GoogleCityResponseDto searchCity(String search); // should find city based on user input

    GooglePoiResponseDto searchRestaurants(City city);

    GooglePoiResponseDto searchHotels(City city);

    GooglePoiResponseDto searchSights(City city);

}
