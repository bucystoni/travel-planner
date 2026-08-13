package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class RestaurantEntity extends PlaceEntity {

    public RestaurantEntity(){}

    public RestaurantEntity(Long id, String name, String address, String url, CityEntity city) {
        super(id, name, address, url, city);
    }
}
