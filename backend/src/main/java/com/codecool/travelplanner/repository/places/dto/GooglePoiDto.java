package com.codecool.travelplanner.repository.places.dto;

public record GooglePoiDto(String id, String formattedAddress, String websiteUri, DisplayName displayName) {
}
