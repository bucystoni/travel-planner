package com.codecool.travelplanner.dto.places;

public record GooglePoiDto(String id, String formattedAddress, String websiteUri, DisplayName displayName) {
}
