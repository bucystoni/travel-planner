package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

@Entity
@Table(name = "sights")
public class SightEntity extends PlaceEntity {

    public SightEntity() {}

    public SightEntity( String name, String address, String url, CityEntity city) {
        super( name, address, url, city);
    }
}