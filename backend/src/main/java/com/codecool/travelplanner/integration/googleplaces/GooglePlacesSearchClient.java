package com.codecool.travelplanner.integration.googleplaces;

import com.codecool.travelplanner.integration.googleplaces.dto.request.GoogleSearchTextRequestDto;
import com.codecool.travelplanner.integration.googleplaces.dto.response.GoogleCityResponseDto;
import com.codecool.travelplanner.integration.googleplaces.dto.response.GooglePoiResponseDto;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.POI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class GooglePlacesSearchClient implements PlacesSearchClient{
    private final RestClient restClient;

    public GooglePlacesSearchClient(RestClient.Builder builder, @Value("${google.places.api-key}") String apiKey) {
        this.restClient = builder
                .baseUrl("https://places.googleapis.com/v1")
                .defaultHeader("X-Goog-Api-Key", apiKey)
                .build();
    }

    @Override
    public City searchCity(String search) { // TODO: Implement CityNotFoundException
        GoogleCityResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.displayName,places.location")
                .body(new GoogleSearchTextRequestDto(search, "locality"))
                .retrieve()
                .body(GoogleCityResponseDto.class);

        List<City> results = GooglePlaceMapper.convertResponseToCityList(response);
        return results.getFirst();
    }


    @Override
    public List<POI> searchRestaurants(City city) {
        String search = "restaurants in " + city.toString();
        GooglePoiResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                .body(new GoogleSearchTextRequestDto(search, "restaurant"))
                .retrieve()
                .body(GooglePoiResponseDto.class);

        return GooglePlaceMapper.convertResponseToPoiList(response);
    }

    @Override
    public List<POI> searchHotels(City city) {
        String search = "hotels in " + city.toString();
        GooglePoiResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                .body(new GoogleSearchTextRequestDto(search, "hotel"))
                .retrieve()
                .body(GooglePoiResponseDto.class);

        return GooglePlaceMapper.convertResponseToPoiList(response);
    }

    @Override
    public List<POI> searchSights(City city) {
        String search = "sights in " + city.toString();
        GooglePoiResponseDto response = restClient.post()
                .uri("/places:searchText")
                .header("X-Goog-FieldMask", "places.id,places.formattedAddress,places.websiteUri,places.displayName")
                .body(new GoogleSearchTextRequestDto(search, "tourist_attraction"))
                .retrieve()
                .body(GooglePoiResponseDto.class);

        return GooglePlaceMapper.convertResponseToPoiList(response);
    }
}
