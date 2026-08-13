package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

@Entity
@Table(name = "accommodations")
public class AccommodationEntity extends PlaceEntity {

    public AccommodationEntity() {}

    public AccommodationEntity(String name, String address, String url, CityEntity city) {
        super(name, address, url, city);
    }
}
