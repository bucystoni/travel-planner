package com.codecool.travelplanner.repository.places.dto;

import com.codecool.travelplanner.model.Location;

public record GoogleCityDto(String id, DisplayName displayName, Location location) {}
