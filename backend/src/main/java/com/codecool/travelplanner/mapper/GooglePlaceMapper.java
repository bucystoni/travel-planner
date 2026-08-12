package com.codecool.travelplanner.mapper;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.PointOfInterest;
import com.codecool.travelplanner.model.PointOfInterestDto;

import java.util.List;

public class GooglePlaceMapper {

    public static PointOfInterestDto convertResponseToDto(GoogleCityResponseDto cityResponse, GooglePoiResponseDto poiResponse) {
        PointOfInterestDto poiDto = new PointOfInterestDto();
        poiDto.setCity(convertResponseToCityList(cityResponse).getFirst());
        poiDto.setPointsOfInterest(convertResponseToPoiList(poiResponse));

        return poiDto;

    }

    private static List<PointOfInterest> convertResponseToPoiList(GooglePoiResponseDto dto) {
        return dto.getPlaces().stream().map(GooglePlaceMapper::convertDtoToPoi).toList();
    }

    private static List<City> convertResponseToCityList(GoogleCityResponseDto dto) {
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
}