package com.codecool.travelplanner.service;

import com.codecool.travelplanner.dto.places.GooglePoiResponseDto;
import com.codecool.travelplanner.mapper.places.PlacesEntityMapper;
import com.codecool.travelplanner.mapper.places.PlacesMapper;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.model.entity.places.CityEntity;
import com.codecool.travelplanner.model.entity.places.SightEntity;
import com.codecool.travelplanner.repository.places.api.PlacesSearchRepository;
import com.codecool.travelplanner.repository.places.sql.AccommodationRepository;
import com.codecool.travelplanner.repository.places.sql.CityRepository;
import com.codecool.travelplanner.repository.places.sql.RestaurantRepository;
import com.codecool.travelplanner.repository.places.sql.SightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.places.PlacesService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlacesServiceTest {

    @Mock
    private PlacesSearchRepository repository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private AccommodationRepository accommodationRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private SightRepository sightRepository;


    private final PlacesMapper mapper = new PlacesMapper();
    private final PlacesEntityMapper entityMapper = new PlacesEntityMapper();

    @Test
    void callsProviderWhenNothingIsCached() {
        CityEntity london = new CityEntity("London", -0.1276, 51.5072);
        City londonDto = new City().name("London").longitude(-0.1276).latitude(51.5072);

        PlacesService service = new PlacesService(
                repository, cityRepository, accommodationRepository,
                restaurantRepository, sightRepository, mapper, entityMapper);

        when(cityRepository.findByName("London")).thenReturn(List.of(london));
        when(sightRepository.findByCityName("London")).thenReturn(List.of());
        when(repository.searchSights(londonDto)).thenReturn(new GooglePoiResponseDto());

        service.getSights("London");

        verify(repository).searchSights(londonDto);
    }

    @Test
    void doesNotCallProviderWhenDataIsCached() {
        CityEntity london = new CityEntity("London", -0.1276, 51.5072);
        SightEntity bigBen = new SightEntity(
                "Big Ben", "London SW1A 0AA, UK", "https://www.parliament.uk/bigben", london);

        PlacesService service = new PlacesService(
                repository, cityRepository, accommodationRepository,
                restaurantRepository, sightRepository, mapper, entityMapper);

        when(cityRepository.findByName("London")).thenReturn(List.of(london));
        when(sightRepository.findByCityName("London")).thenReturn(List.of(bigBen));   // már van cache

        service.getSights("London");

        verifyNoInteractions(repository);
    }
}
