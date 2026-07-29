package com.codecool.travelplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastEntry {

    @JsonProperty("dt_txt")
    private String dateText;

    private MainInfo main;

    private List<WeatherInfo> weather;

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }
}
