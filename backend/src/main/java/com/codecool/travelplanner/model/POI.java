package com.codecool.travelplanner.model;

public class POI extends Place {
    private final String address;
    private final String websiteUri;

    public POI(String id, String name, String address, String websiteUri) {
        super(id, name);
        this.address = address;
        this.websiteUri = websiteUri;
    }
}
