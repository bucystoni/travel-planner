package com.codecool.travelplanner.integration.googleplaces.dto.response;

import com.codecool.travelplanner.model.Location;

public record GoogleCityDto(String id, DisplayName displayName, Location location) {}
