package com.codecool.travelplanner.mapper.places;

import com.codecool.travelplanner.dto.places.GoogleCityDto;
import com.codecool.travelplanner.dto.places.GooglePoiDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.model.entity.places.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlacesEntityMapper {

    public List<AccommodationEntity> toAccommodations(GooglePoiResponseDto response, CityEntity city) {
        return response.getPlaces().stream().map(place -> toAccommodation(place, city)).toList();
    }

    public List<RestaurantEntity> toRestaurants(GooglePoiResponseDto response, CityEntity city) {
        return response.getPlaces()
                .stream()
                .map(place -> toRestaurant(place, city)).toList();
    }

    public List<SightEntity> toSights(GooglePoiResponseDto response, CityEntity city) {
        return response.getPlaces()
                .stream()
                .map(place -> toSight(place, city)).toList();
    }

    private AccommodationEntity toAccommodation(GooglePoiDto poiDto, CityEntity city) {
        return new AccommodationEntity(poiDto.displayName().text(), poiDto.formattedAddress(), poiDto.websiteUri(), city);
    }

    private RestaurantEntity toRestaurant(GooglePoiDto poiDto, CityEntity city) {
        return new RestaurantEntity(poiDto.displayName().text(), poiDto.formattedAddress(), poiDto.websiteUri(), city);
    }

    private SightEntity toSight(GooglePoiDto poiDto, CityEntity city) {
        return new SightEntity(poiDto.displayName().text(), poiDto.formattedAddress(), poiDto.websiteUri(), city);
    }

    public CityEntity toCity(GoogleCityDto cityDto) {
        return new CityEntity(cityDto.displayName().text(), cityDto.location().longitude(), cityDto.location().latitude());
    }
}
