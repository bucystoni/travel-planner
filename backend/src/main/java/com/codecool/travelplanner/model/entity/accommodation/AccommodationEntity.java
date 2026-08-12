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

    public AccommodationEntity() {}

    public AccommodationEntity(Long id, String name, String address, String url) {
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
}
