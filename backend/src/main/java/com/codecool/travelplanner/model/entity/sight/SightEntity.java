package com.codecool.travelplanner.model.entity.sight;

import com.codecool.travelplanner.model.entity.city.CityEntity;
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

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    public SightEntity() {}

    public SightEntity(Long id, String name, String address, String url) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.url = url;
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