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

    public PointOfInterest convertPlaceEntityToPointOfInterest(PlaceEntity entity) {

        PointOfInterest place = new PointOfInterest();
        place.setId(entity.getId());
        place.setName(entity.getName());
        place.setAddress(entity.getAddress());
        place.setUrl(entity.getUrl());

        return place;
    }

    public static City convertResponseToCity(CityEntity entity) {
        City city = new City();
        city.setName(entity.getName());
        city.setLatitude(entity.getLatitude());
        city.setLongitude(entity.getLongitude());

        return city;
    }
}