package com.codecool.travelplanner.integration.googleplaces;

import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.POI;

import java.util.List;

public interface PlacesSearchClient {
    City searchCity(String search); // should find city based on user input

    List<POI> searchRestaurants(City city);

    List<POI> searchHotels(City city);

    List<POI> searchSights(City city);

}
