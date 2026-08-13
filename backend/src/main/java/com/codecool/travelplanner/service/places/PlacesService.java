package com.codecool.travelplanner.service.places;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GooglePoiDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.exception.CityNotFoundException;
import com.codecool.travelplanner.mapper.places.PlacesEntityMapper;
import com.codecool.travelplanner.mapper.places.PlacesMapper;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.PointOfInterest;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.model.entity.places.*;
import com.codecool.travelplanner.repository.places.api.PlacesSearchRepository;
import com.codecool.travelplanner.repository.places.sql.AccommodationRepository;
import com.codecool.travelplanner.repository.places.sql.CityRepository;
import com.codecool.travelplanner.repository.places.sql.RestaurantRepository;
import com.codecool.travelplanner.repository.places.sql.SightRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlacesService {
    private final PlacesSearchRepository repository;
    private final CityRepository cityRepository;
    private final AccommodationRepository accommodationRepository;
    private final RestaurantRepository restaurantRepository;
    private final SightRepository sightRepository;
    private final PlacesMapper mapper;
    private final PlacesEntityMapper entityMapper;

    public PlacesService(PlacesSearchRepository repository, CityRepository cityRepository, AccommodationRepository accommodationRepository, RestaurantRepository restaurantRepository, SightRepository sightRepository, PlacesMapper mapper, PlacesEntityMapper entityMapper) {
        this.repository = repository;
        this.cityRepository = cityRepository;
        this.accommodationRepository = accommodationRepository;
        this.restaurantRepository = restaurantRepository;
        this.sightRepository = sightRepository;
        this.mapper = mapper;
        this.entityMapper = entityMapper;
    }

    public CityEntity searchCity(String destinationName) {
        CityEntity cityEntity;
        List<CityEntity> databaseCities = cityRepository.findByName(destinationName);

        if (!databaseCities.isEmpty()) {
            cityEntity = databaseCities.getFirst();
            return cityEntity;
        } else {
            GoogleCityResponseDto cityResponse = repository.searchCity(destinationName);
            if (cityResponse.getPlaces() == null || cityResponse.getPlaces().isEmpty()) {
                throw new CityNotFoundException(destinationName);
            }
            GoogleCityDto cityDto = cityResponse.getPlaces().getFirst();

            cityEntity = entityMapper.toCity(cityDto);
            cityRepository.save(cityEntity);

            return cityEntity;
        }
    }

    public PointOfInterestDto getAccommodations(String destinationName) {
        PointOfInterestDto dto;

        CityEntity cityEntity = searchCity(destinationName);
        City cityDto = mapper.convertResponseToCity(cityEntity);

        Optional<List<AccommodationEntity>> databaseAccommodations = accommodationRepository.findByCityName(destinationName);

        if (databaseAccommodations.isPresent()) {
            List<PlaceEntity> places = new ArrayList<>(databaseAccommodations.get());
            dto = mapper.convertResponseToDto(cityDto, places);
            return dto;

        } else {
            GooglePoiResponseDto accommodations = repository.searchAccomodations(cityDto);

            List<AccommodationEntity> accommodationEntities = entityMapper.toAccommodations(accommodations, cityEntity);
            accommodationRepository.saveAll(accommodationEntities);

            dto = mapper.convertResponseToDto(cityDto, accommodations);
            return dto;
        }

    }

    public PointOfInterestDto getRestaurants(String destinationName) {
        PointOfInterestDto dto;

        CityEntity cityEntity = searchCity(destinationName);
        City cityDto = mapper.convertResponseToCity(cityEntity);

        Optional<List<RestaurantEntity>> databaseRestaurants = restaurantRepository.findByCityName(destinationName);

        if (databaseRestaurants.isPresent()){
            List<PlaceEntity> places = new ArrayList<>(databaseRestaurants.get());
            dto = mapper.convertResponseToDto(cityDto, places);
            return dto;

        } else{
            GooglePoiResponseDto restaurants = repository.searchRestaurants(cityDto);
            List<RestaurantEntity> restaurantEntities = entityMapper.toRestaurants(restaurants, cityEntity);
            restaurantRepository.saveAll(restaurantEntities);

            dto = mapper.convertResponseToDto(cityDto, restaurants);
            return dto;
        }
    }

    public PointOfInterestDto getSights(String destinationName) {
        PointOfInterestDto dto;

        CityEntity cityEntity = searchCity(destinationName);
        City cityDto = mapper.convertResponseToCity(cityEntity);

        Optional<List<SightEntity>> databaseSights = sightRepository.findByCityName(destinationName);

        if (databaseSights.isPresent()) {
            List<PlaceEntity> places = new ArrayList<>(databaseSights.get());
            dto = mapper.convertResponseToDto(cityDto, places);
            return dto;

        } else {
            GooglePoiResponseDto sights = repository.searchSights(cityDto);
            List<SightEntity> sightEntities = entityMapper.toSights(sights, cityEntity);
            sightRepository.saveAll(sightEntities);

            dto = mapper.convertResponseToDto(cityDto, sights);
            return dto;
        }
    }
}
