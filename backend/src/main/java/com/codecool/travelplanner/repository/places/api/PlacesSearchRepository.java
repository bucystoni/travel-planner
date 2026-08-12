package com.codecool.travelplanner.repository.places.api;

import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;

public interface PlacesSearchRepository {
    GoogleCityResponseDto searchCity(String search); // should find city based on user input

    GooglePoiResponseDto searchRestaurants(City city);

    GooglePoiResponseDto searchAccomodations(City city);

    GooglePoiResponseDto searchSights(City city);

}
