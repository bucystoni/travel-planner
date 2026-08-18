package com.codecool.travelplanner.service.places;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.mapper.places.PlacesEntityMapper;
import com.codecool.travelplanner.model.entity.places.AccommodationEntity;
import com.codecool.travelplanner.model.entity.places.CityEntity;
import com.codecool.travelplanner.model.entity.places.RestaurantEntity;
import com.codecool.travelplanner.model.entity.places.SightEntity;
import com.codecool.travelplanner.repository.places.sql.AccommodationRepository;
import com.codecool.travelplanner.repository.places.sql.CityRepository;
import com.codecool.travelplanner.repository.places.sql.RestaurantRepository;
import com.codecool.travelplanner.repository.places.sql.SightRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

@Service
public class PlacesDataImportService {

    private final ObjectMapper objectMapper;
    private final PlacesEntityMapper placesEntityMapper;
    private final AccommodationRepository accommodationRepository;
    private final RestaurantRepository restaurantRepository;
    private final SightRepository sightRepository;
    private final CityRepository cityRepository;

    public PlacesDataImportService(ObjectMapper objectMapper, PlacesEntityMapper placesEntityMapper, AccommodationRepository accommodationRepository, RestaurantRepository restaurantRepository, SightRepository sightRepository, CityRepository cityRepository) {
        this.objectMapper = objectMapper;
        this.placesEntityMapper = placesEntityMapper;
        this.accommodationRepository = accommodationRepository;
        this.restaurantRepository = restaurantRepository;
        this.sightRepository = sightRepository;
        this.cityRepository = cityRepository;
    }

    public void importPlacesData() {
        GoogleCityDto cityDto = getCity();

        CityEntity cityEntity = cityRepository.findByName(cityDto.displayName().text())
                .stream().findFirst()
                .orElseGet(() -> cityRepository.save(placesEntityMapper.toCity(cityDto)));

        List<AccommodationEntity> accommodations = placesEntityMapper.toAccommodations(getAccommodation(), cityEntity);
        List<RestaurantEntity> restaurants = placesEntityMapper.toRestaurants(getRestaurant(), cityEntity);
        List<SightEntity> sights = placesEntityMapper.toSights(getSight(), cityEntity);

        accommodationRepository.saveAll(accommodations);
        restaurantRepository.saveAll(restaurants);
        sightRepository.saveAll(sights);
    }

    private GooglePoiResponseDto getAccommodation() {
        try {
            ClassPathResource rescource = new ClassPathResource("mock-data/hotels-london.json");
            return objectMapper.readValue(rescource.getInputStream(), GooglePoiResponseDto.class);

        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    private GooglePoiResponseDto getRestaurant() {
        try {
            ClassPathResource rescource = new ClassPathResource("mock-data/restaurants-london.json");
            return objectMapper.readValue(rescource.getInputStream(), GooglePoiResponseDto.class);

        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    private GooglePoiResponseDto getSight() {
        try {
            ClassPathResource rescource = new ClassPathResource("mock-data/sights-london.json");
            return objectMapper.readValue(rescource.getInputStream(), GooglePoiResponseDto.class);

        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    private GoogleCityDto getCity() {
        try {
            ClassPathResource rescource = new ClassPathResource("mock-data/london.json");
            GoogleCityResponseDto responses = objectMapper.readValue(rescource.getInputStream(), GoogleCityResponseDto.class);
            return responses.getPlaces().getFirst();

        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    public boolean isDatabaseInitialized() {
        return !cityRepository.findByName("London").isEmpty();
    }
}
