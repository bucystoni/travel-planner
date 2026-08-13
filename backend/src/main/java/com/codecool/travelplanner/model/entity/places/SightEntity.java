package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

@Entity
@Table(name = "sights")
public class SightEntity extends PlaceEntity {

    public SightEntity() {}

    public SightEntity(Long id, String name, String address, String url, CityEntity city) {
        super(id, name, address, url, city);
    }
}