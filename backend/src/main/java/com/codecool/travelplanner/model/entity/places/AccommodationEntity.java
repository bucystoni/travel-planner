package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

@Entity
@Table(name = "accommodations")
public class AccommodationEntity extends PlaceEntity {

    public AccommodationEntity() {}

    public AccommodationEntity(Long id, String name, String address, String url, CityEntity city) {
        super(id, name, address, url, city);
    }
}
