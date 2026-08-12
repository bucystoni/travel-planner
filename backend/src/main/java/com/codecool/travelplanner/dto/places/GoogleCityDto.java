package com.codecool.travelplanner.dto.places;

import com.codecool.travelplanner.model.Location;

public record GoogleCityDto(String id, DisplayName displayName, Location location) {}
