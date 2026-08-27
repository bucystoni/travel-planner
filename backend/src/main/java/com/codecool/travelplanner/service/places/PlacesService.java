package com.codecool.travelplanner.service.places;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.exception.CityNotFoundException;
import com.codecool.travelplanner.mapper.places.PlacesEntityMapper;
import com.codecool.travelplanner.mapper.places.PlacesMapper;
import com.codecool.travelplanner.model.City;
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
        } else {
            GoogleCityResponseDto cityResponse = repository.searchCity(destinationName);
            if (cityResponse.getPlaces() == null || cityResponse.getPlaces().isEmpty()) {
                throw new CityNotFoundException(destinationName);
            }
            GoogleCityDto cityDto = cityResponse.getPlaces().getFirst();

            cityEntity = entityMapper.toCity(cityDto);
            cityRepository.save(cityEntity);

        }
        return cityEntity;
    }

    public PointOfInterestDto getAccommodations(String destinationName) {
        PointOfInterestDto dto;

        CityEntity cityEntity = searchCity(destinationName);
        City cityDto = PlacesMapper.convertResponseToCity(cityEntity);

        List<AccommodationEntity> databaseAccommodations = accommodationRepository.findByCityName(cityEntity.getName());

        if (!databaseAccommodations.isEmpty()) {
            List<PlaceEntity> places = new ArrayList<>(databaseAccommodations);
            dto = mapper.convertResponseToDto(cityDto, places);

        } else {
            GooglePoiResponseDto accommodations = repository.searchAccomodations(cityDto);

            List<AccommodationEntity> accommodationEntities = entityMapper.toAccommodations(accommodations, cityEntity);
            List<AccommodationEntity> saved = accommodationRepository.saveAll(accommodationEntities);
            List<PlaceEntity> savedAccommodations = new ArrayList<>(saved);

            dto = mapper.convertResponseToDto(cityDto, savedAccommodations);
        }
        return dto;
    }

    public PointOfInterestDto getRestaurants(String destinationName) {
        PointOfInterestDto dto;

        CityEntity cityEntity = searchCity(destinationName);
        City cityDto = PlacesMapper.convertResponseToCity(cityEntity);

        List<RestaurantEntity> databaseRestaurants = restaurantRepository.findByCityName(cityEntity.getName());

        if (!databaseRestaurants.isEmpty()){
            List<PlaceEntity> places = new ArrayList<>(databaseRestaurants);
            dto = mapper.convertResponseToDto(cityDto, places);

        } else{
            GooglePoiResponseDto restaurants = repository.searchRestaurants(cityDto);
            List<RestaurantEntity> restaurantEntities = entityMapper.toRestaurants(restaurants, cityEntity);
            List<RestaurantEntity> saved = restaurantRepository.saveAll(restaurantEntities);
            List<PlaceEntity> savedRestaurants = new ArrayList<>(saved);

            dto = mapper.convertResponseToDto(cityDto, savedRestaurants);
        }
        return dto;
    }

    public PointOfInterestDto getSights(String destinationName) {
        PointOfInterestDto dto;

        CityEntity cityEntity = searchCity(destinationName);
        City cityDto = PlacesMapper.convertResponseToCity(cityEntity);

        List<SightEntity> databaseSights = sightRepository.findByCityName(cityEntity.getName());

        if (!databaseSights.isEmpty()) {
            List<PlaceEntity> places = new ArrayList<>(databaseSights);
            dto = mapper.convertResponseToDto(cityDto, places);

        } else {
            GooglePoiResponseDto sights = repository.searchSights(cityDto);
            List<SightEntity> sightEntities = entityMapper.toSights(sights, cityEntity);
            List<SightEntity> saved = sightRepository.saveAll(sightEntities);
            List<PlaceEntity> savedSights = new ArrayList<>(saved);

            dto = mapper.convertResponseToDto(cityDto, savedSights);
        }
        return dto;
    }
}
