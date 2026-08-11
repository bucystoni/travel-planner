package com.codecool.travelplanner.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MainInfo(
        double temp,
        @JsonProperty("temp_min") double tempMin,
        @JsonProperty("temp_max") double tempMax) {
}
