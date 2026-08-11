package com.codecool.travelplanner.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(List<ForecastEntry> list) {
}