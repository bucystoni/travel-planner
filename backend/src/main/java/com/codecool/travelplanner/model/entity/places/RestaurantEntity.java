package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class RestaurantEntity extends PlaceEntity {

    public RestaurantEntity(){}

    public RestaurantEntity(String name, String address, String url, CityEntity city) {
        super( name, address, url, city);
    }
}
