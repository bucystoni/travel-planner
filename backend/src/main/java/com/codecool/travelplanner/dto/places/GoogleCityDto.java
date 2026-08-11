package com.codecool.travelplanner.integration.googleplaces.dto;

import com.codecool.travelplanner.model.Location;

public record GoogleCityDto(String id, DisplayName displayName, Location location) {}
