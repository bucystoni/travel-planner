package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
public class CityEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double longitude;

    private double latitude;

    @OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST)
    private List<RestaurantEntity> restaurants = new ArrayList<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST)
    private List<AccommodationEntity> accommodations = new ArrayList<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST)
    private List<SightEntity> sights = new ArrayList<>();

    public CityEntity() {}

    public CityEntity(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<AccommodationEntity> getAccommodations() {
        return accommodations;
    }

    public List<RestaurantEntity> getRestaurants() {
        return restaurants;
    }

    public List<SightEntity> getSights() {
        return sights;
    }
}

