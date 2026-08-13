package com.codecool.travelplanner.service.places;

import com.codecool.travelplanner.mapper.places.PlacesMapper;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.repository.places.api.PlacesSearchRepository;
import org.springframework.stereotype.Service;


@Service
public class PlacesService {
    private final PlacesSearchRepository repository;
    private City currentCity;

    public PlacesService(PlacesSearchRepository repository) {
        this.repository = repository;
        this.currentCity = null;
    }

    public City searchCity(String destinationName) {
        currentCity = PlacesMapper.convertResponseToCityList(repository.searchCity(destinationName)).getFirst();
        return currentCity;
    }

    public PointOfInterestDto getAccommodations(String destinationName) {
        City city = searchCity(destinationName);
        return PlacesMapper.convertResponseToDto(city, repository.searchAccomodations(city));
    }

    public PointOfInterestDto getRestaurants(String destinationName) {
        City city = searchCity(destinationName);
        return PlacesMapper.convertResponseToDto(city, repository.searchRestaurants(city));
    }

    public PointOfInterestDto getSights(String destinationName) {
        City city = searchCity(destinationName);
        return PlacesMapper.convertResponseToDto(city, repository.searchSights(city));
    }
}
