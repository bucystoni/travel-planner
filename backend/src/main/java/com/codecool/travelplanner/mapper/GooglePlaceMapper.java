package com.codecool.travelplanner.mapper;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.POI;

import java.util.List;

public class GooglePlaceMapper {
    public static City convertDtoToCity(GoogleCityDto dto) {
        return new City(dto.id(), dto.displayName().text(), dto.location());
    }

    public static POI convertDtoToPoi(GooglePoiDto dto) {
        return new POI(dto.id(), dto.displayName().text(), dto.formattedAddress(), dto.websiteUri());
    }

    public static List<POI> convertResponseToPoiList(GooglePoiResponseDto dto) {
        return dto.getPlaces().stream().map(GooglePlaceMapper::convertDtoToPoi).toList();
    }

    public static List<City> convertResponseToCityList(GoogleCityResponseDto dto) {
        return dto.getPlaces().stream().map(GooglePlaceMapper::convertDtoToCity).toList();
    }
}