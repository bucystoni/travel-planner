package com.codecool.travelplanner.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IgnavConfig {

    private final String baseUrl;
    private final String apiKey;

    public IgnavConfig(
            @Value("${ignav.base-url}") String baseUrl,
            @Value("${IGNAV_API_KEY}") String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }


}
