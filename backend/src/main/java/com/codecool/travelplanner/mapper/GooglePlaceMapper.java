package com.codecool.travelplanner.mapper;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.PointOfInterest;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.model.entity.places.PlaceEntity;


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

    public PointOfInterestDto convertPlaceEntityToPointOfInterestDto(List<PlaceEntity> entities) {
        City city = new City();
        city.setName(entities.getFirst().getCity().getName());
        city.setLongitude(entities.getFirst().getCity().getLongitude());
        city.setLatitude(entities.getFirst().getCity().getLatitude());

        List<PointOfInterest> places = entities.stream()
                .map(this::convertPlaceEntityToPointOfInterest).toList();

        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(city);
        poiDto.setPointsOfInterest(places);

        return poiDto;
    }

    private PointOfInterest convertPlaceEntityToPointOfInterest(PlaceEntity entity) {
        PointOfInterest place = new PointOfInterest();
        place.setName(entity.getName());
        place.setAddress(entity.getAddress());
        place.setUrl(entity.getUrl());
        return place;
    }
}