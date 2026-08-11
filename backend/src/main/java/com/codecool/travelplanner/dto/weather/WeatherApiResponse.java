package com.codecool.travelplanner.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(@JsonProperty("list") List<ForecastEntry> forecasts) {
}