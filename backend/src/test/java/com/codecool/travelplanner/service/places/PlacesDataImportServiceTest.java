package com.codecool.travelplanner.service.places;

import com.codecool.travelplanner.model.entity.places.CityEntity;
import com.codecool.travelplanner.repository.places.sql.AccommodationRepository;
import com.codecool.travelplanner.repository.places.sql.CityRepository;
import com.codecool.travelplanner.repository.places.sql.RestaurantRepository;
import com.codecool.travelplanner.repository.places.sql.SightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.places.PlacesDataImportService;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlacesDataImportServiceTest {

    @Mock
    private AccommodationRepository accommodationRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private SightRepository sightRepository;
    @Mock
    private CityRepository cityRepository;

    private final JsonMapper objectMapper = JsonMapper.builder().build();

    @Test
    void isDatabaseInitializedReturnsTrueWhenLondonAlreadyStored() {
        PlacesDataImportService service = new PlacesDataImportService(
                objectMapper, null, accommodationRepository,
                restaurantRepository, sightRepository, cityRepository);

        when(cityRepository.findByName("London"))
                .thenReturn(List.of(new CityEntity("London", -0.1276, 51.5072)));

        assertThat(service.isDatabaseInitialized()).isTrue();
    }

    @Test
    void isDatabaseInitializedReturnsFalseWhenLondonMissing() {
        PlacesDataImportService service = new PlacesDataImportService(
                objectMapper, null, accommodationRepository,
                restaurantRepository, sightRepository, cityRepository);

        when(cityRepository.findByName("London")).thenReturn(List.of());

        assertThat(service.isDatabaseInitialized()).isFalse();
    }
}
