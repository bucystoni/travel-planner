package com.codecool.travelplanner.model;

public class Place {
    private final String id;
    private final String name;

    public Place(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
