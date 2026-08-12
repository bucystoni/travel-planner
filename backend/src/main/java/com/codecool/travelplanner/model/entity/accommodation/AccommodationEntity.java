package com.codecool.travelplanner.model.entity.accommodation;

import jakarta.persistence.*;

@Entity
@Table(name = "accommodations")
public class AccommodationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String url;

    @Column(name = "city_name")
    private String cityName;

    public AccommodationEntity() {}

    public AccommodationEntity(Long id, String name, String address, String url, String cityName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.url = url;
        this.cityName = cityName;
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

    public String getCityName() {
        return cityName;
    }
}
