package com.codecool.travelplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {

    private List<ForecastEntry> list;

    public List<ForecastEntry> getList() {
        return list;
    }

    public void setList(List<ForecastEntry> list) {
        this.list = list;
    }
}
