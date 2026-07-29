package com.codecool.travelplanner.integration.googleplaces.dto;

public record GooglePoiDto(String id, String formattedAddress, String websiteUri, DisplayName displayName) {
}
