package com.codecool.travelplanner.mapper;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.PointOfInterest;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.model.entity.accommodation.AccommodationEntity;
import com.codecool.travelplanner.model.entity.restaurant.RestaurantEntity;
import com.codecool.travelplanner.model.entity.sight.SightEntity;

import java.util.List;

public class GooglePlaceMapper {

             // ==================== GOOGLE API --> PointOfInterestDto ==================== //

    public static PointOfInterestDto convertResponseToDto(City city, GooglePoiResponseDto poiResponse) {
        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(city);
        poiDto.setPointsOfInterest(convertResponseToPoiList(poiResponse));

        return poiDto;

    }

    private static List<PointOfInterest> convertResponseToPoiList(GooglePoiResponseDto dto) {
        return dto.getPlaces().stream().map(GooglePlaceMapper::convertDtoToPoi).toList();
    }

    public static List<City> convertResponseToCityList(GoogleCityResponseDto dto) {
        return dto.getPlaces().stream().map(GooglePlaceMapper::convertDtoToCity).toList();
    }

    private static City convertDtoToCity(GoogleCityDto dto) {
        City city = new City();
        city.setName(dto.displayName().text());
        city.setLatitude(dto.location().latitude());
        city.setLongitude(dto.location().longitude());

        return city;
    }

    private static PointOfInterest convertDtoToPoi(GooglePoiDto dto) {
        PointOfInterest poi = new PointOfInterest();
        poi.setName(dto.displayName().text());
        poi.setAddress(dto.formattedAddress());
        poi.setUrl(dto.websiteUri());

        return poi;
    }

        // ==================== SQL Entity --> PointOfInterestDto ==================== //

    public PointOfInterestDto convertAccommodationEntityToPointOfInterestDto(List<AccommodationEntity> entities) {
        City city = new City();
        city.setName(entities.getFirst().getCity().getName());
        city.setLongitude(entities.getFirst().getCity().getLongitude());
        city.setLatitude(entities.getFirst().getCity().getLatitude());

        List<PointOfInterest> accommodations = entities.stream()
                .map(this::convertAccommodationEntityToPointOfInterest).toList();

        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(city);
        poiDto.setPointsOfInterest(accommodations);

        return poiDto;
    }

    private PointOfInterest convertAccommodationEntityToPointOfInterest(AccommodationEntity accommodationEntity) {
        PointOfInterest accommodation = new PointOfInterest();
        accommodation.setName(accommodationEntity.getName());
        accommodation.setAddress(accommodationEntity.getAddress());
        accommodation.setUrl(accommodationEntity.getUrl());
        return accommodation;
    }

    public PointOfInterestDto convertRestaurantEntityToPointOfInterestDto(List<RestaurantEntity> entities) {
        City city = new City();
        city.setName(entities.getFirst().getCity().getName());
        city.setLongitude(entities.getFirst().getCity().getLongitude());
        city.setLatitude(entities.getFirst().getCity().getLatitude());

        List<PointOfInterest> restaurants = entities.stream()
                .map(this::convertRestaurantEntityToPointOfInterest).toList();

        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(city);
        poiDto.setPointsOfInterest(restaurants);

        return poiDto;
    }

    private PointOfInterest convertRestaurantEntityToPointOfInterest(RestaurantEntity restaurantEntity) {
        PointOfInterest restaurant = new PointOfInterest();
        restaurant.setName(restaurantEntity.getName());
        restaurant.setAddress(restaurantEntity.getAddress());
        restaurant.setUrl(restaurantEntity.getUrl());
        return restaurant;
    }

    public PointOfInterestDto convertSightEntityToPointOfInterestDto(List<SightEntity> entities) {
        City city = new City();
        city.setName(entities.getFirst().getCity().getName());
        city.setLongitude(entities.getFirst().getCity().getLongitude());
        city.setLatitude(entities.getFirst().getCity().getLatitude());

        List<PointOfInterest> sights = entities.stream()
                .map(this::convertSightEntityToPointOfInterest).toList();

        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(city);
        poiDto.setPointsOfInterest(sights);

        return poiDto;
    }

    private PointOfInterest convertSightEntityToPointOfInterest(SightEntity sightEntity) {
        PointOfInterest sight = new PointOfInterest();
        sight.setName(sightEntity.getName());
        sight.setAddress(sightEntity.getAddress());
        sight.setUrl(sightEntity.getUrl());
        return sight;
    }
}