package com.codecool.travelplanner.integration.googleplaces.dto.response;

public record GooglePoiDto(String id, String formattedAddress, String websiteUri, DisplayName displayName) {
}
