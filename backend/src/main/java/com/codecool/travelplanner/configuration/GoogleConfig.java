package com.codecool.travelplanner.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleConfig {
    private final String baseUrl;
    private final String apiKey;

    public GoogleConfig(
            @Value("${googleplaces.base-url}") String baseUrl,
            @Value("${GOOGLE_API_KEY}") String apiKey) {
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
