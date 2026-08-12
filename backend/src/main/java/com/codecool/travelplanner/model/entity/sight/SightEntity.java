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
}