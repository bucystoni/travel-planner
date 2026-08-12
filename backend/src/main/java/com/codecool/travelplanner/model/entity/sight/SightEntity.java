package com.codecool.travelplanner.model.entity.sight;

import jakarta.persistence.*;

@Entity
@Table(name = "sights")
public class SightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String url;

    @Column(name = "city_name")
    private String cityName;

    public SightEntity() {}

    public SightEntity(Long id, String name, String address, String url, String cityName) {
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