package com.codecool.travelplanner.mapper.places;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.PointOfInterest;
import com.codecool.travelplanner.model.PointOfInterestDto;
import com.codecool.travelplanner.model.entity.places.CityEntity;
import com.codecool.travelplanner.model.entity.places.PlaceEntity;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PlacesMapper {

    // ==================== GOOGLE API --> PointOfInterestDto ==================== //

    public static PointOfInterestDto convertResponseToDto(City city, GooglePoiResponseDto poiResponse) {

        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(city);
        poiDto.setPointsOfInterest(convertResponseToPoiList(poiResponse));

        return poiDto;
    }

    private static List<PointOfInterest> convertResponseToPoiList(
            GooglePoiResponseDto dto) {

        return dto.getPlaces()
                .stream()
                .map(PlacesMapper::convertDtoToPoi)
                .toList();
    }

    public static List<City> convertResponseToCityList(
            GoogleCityResponseDto dto) {

        return dto.getPlaces()
                .stream()
                .map(PlacesMapper::convertDtoToCity)
                .toList();
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

    public PointOfInterestDto convertResponseToDto(City city, List<PlaceEntity> entities) {

        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(city);
        poiDto.setPointsOfInterest(
                entities.stream()
                        .map(this::convertPlaceEntityToPointOfInterest)
                        .toList()
        );

        return poiDto;
    }

    private PointOfInterest convertPlaceEntityToPointOfInterest(PlaceEntity entity) {

        PointOfInterest place = new PointOfInterest();
        place.setName(entity.getName());
        place.setAddress(entity.getAddress());
        place.setUrl(entity.getUrl());

        return place;
    }

    public City convertResponseToCity(CityEntity entity) {
        City city = new City();
        city.setName(entity.getName());
        city.setLatitude(entity.getLatitude());
        city.setLongitude(entity.getLongitude());

        return city;
    }
}