package com.codecool.travelplanner.dto.places;

import com.codecool.travelplanner.repository.places.model.Location;

public record GoogleCityDto(String id, DisplayName displayName, Location location) {}
