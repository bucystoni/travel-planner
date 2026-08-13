package com.codecool.travelplanner.model.entity.places;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String url;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    public PlaceEntity() {}

    public PlaceEntity(Long id, String name, String address, String url, CityEntity city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.url = url;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public CityEntity getCity() {
        return city;
    }
}
