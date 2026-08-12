package com.codecool.travelplanner.repository.places;


import com.codecool.travelplanner.dto.places.GoogleCityResponseDto;
import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.dto.places.GoogleSearchTextRequestDto;
import com.codecool.travelplanner.model.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


public class GooglePlacesSearchClient implements PlacesSearchRepository {
    private final RestClient restClient;

    public GooglePlacesSearchClient(RestClient.Builder builder, @Value("${google.places.api-key}") String apiKey) {
        this.restClient = builder
                .baseUrl("https://places.googleapis.com/v1")
                .defaultHeader("X-Goog-Api-Key", apiKey)
                .build();
    }

    @Override
    public GoogleCityResponseDto searchCity(String cityName) { // TODO: Implement CityNotFoundException
        GoogleCityResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.displayName,places.location")
                .body(new GoogleSearchTextRequestDto(cityName, "locality"))
                .retrieve()
                .body(GoogleCityResponseDto.class);

        return response;
    }


    @Override
    public GooglePoiResponseDto searchRestaurants(City city) {
        String search = "restaurants in " + city.getName();
        GooglePoiResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                .body(new GoogleSearchTextRequestDto(search, "restaurant"))
                .retrieve()
                .body(GooglePoiResponseDto.class);

        return response;
    }

    @Override
    public GooglePoiResponseDto searchHotels(City city) {
        String search = "hotels in " + city.getName();
        GooglePoiResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                .body(new GoogleSearchTextRequestDto(search, "hotel"))
                .retrieve()
                .body(GooglePoiResponseDto.class);

        return response;
    }

    @Override
    public GooglePoiResponseDto searchSights(City city) {
        String search = "sights in " + city.getName();
        GooglePoiResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                .body(new GoogleSearchTextRequestDto(search, "tourist_attraction"))
                .retrieve()
                .body(GooglePoiResponseDto.class);

        return response;
    }
}
