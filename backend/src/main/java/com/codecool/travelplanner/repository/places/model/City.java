package com.codecool.travelplanner.repository.places.model;

public class City extends Place {
    private final Location location;

    public City(String id, String name, Location location) {
        super(id, name);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
