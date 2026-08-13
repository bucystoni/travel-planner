package com.codecool.travelplanner.repository.places.api;


import com.codecool.travelplanner.configuration.GoogleConfig;
import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.dto.places.GoogleSearchTextRequestDto;
import com.codecool.travelplanner.exception.PlacesApiException;
import com.codecool.travelplanner.model.City;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class GooglePlacesSearchRepository implements PlacesSearchRepository {
    private final RestClient restClient;
    private final GoogleConfig config;

    public GooglePlacesSearchRepository(RestClient.Builder builder, GoogleConfig config) {
        this.config = config;
        this.restClient = builder
                .baseUrl(config.getBaseUrl())
                .defaultHeader("X-Goog-Api-Key", config.getApiKey())
                .build();
    }

    @Override
    public GoogleCityResponseDto searchCity(String cityName) {
        try {
            GoogleCityResponseDto response = restClient.post()
                    .uri("/places:searchText")
                    .header("X-Goog-FieldMask", "places.id,places.displayName,places.location")
                    .body(new GoogleSearchTextRequestDto(cityName, "locality"))
                    .retrieve()
                    .body(GoogleCityResponseDto.class);
            return response;
        } catch (RestClientException e) {
            throw new PlacesApiException("Google Places API encountered an issue", e);
        }
    }


    @Override
    public GooglePoiResponseDto searchRestaurants(City city) {
        try {
            String search = "restaurants in " + city.getName();
            GooglePoiResponseDto response = restClient.post()
                    .uri("/places:searchText")
                    .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                    .body(new GoogleSearchTextRequestDto(search, "restaurant"))
                    .retrieve()
                    .body(GooglePoiResponseDto.class);

            return response;
        } catch (RestClientException e) {
            throw new PlacesApiException("Google Places API encountered an issue", e);
        }
    }

    @Override
    public GooglePoiResponseDto searchAccomodations(City city) {
        try {
            String search = "hotels in " + city.getName();
            GooglePoiResponseDto response = restClient.post()
                    .uri("/places:searchText")
                    .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                    .body(new GoogleSearchTextRequestDto(search, "hotel"))
                    .retrieve()
                    .body(GooglePoiResponseDto.class);
            return response;
        } catch (RestClientException e) {
            throw new PlacesApiException("Google Places API encountered an issue", e);
        }
    }

    @Override
    public GooglePoiResponseDto searchSights(City city) {
        try {
            String search = "sights in " + city.getName();
            GooglePoiResponseDto response = restClient.post()
                    .uri("/places:searchText")
                    .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                    .body(new GoogleSearchTextRequestDto(search, "tourist_attraction"))
                    .retrieve()
                    .body(GooglePoiResponseDto.class);

            return response;
        } catch (RestClientException e) {
            throw new PlacesApiException("Google Places API encountered an issue", e);
        }
    }
}
