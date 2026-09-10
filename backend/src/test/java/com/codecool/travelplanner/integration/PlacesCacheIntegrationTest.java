package com.codecool.travelplanner.integration;

import com.codecool.travelplanner.BackendApplication;
import com.codecool.travelplanner.dto.places.*;
import com.codecool.travelplanner.model.City;
import com.codecool.travelplanner.repository.places.api.PlacesSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BackendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
public class PlacesCacheIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private PlacesSearchRepository placesSearchRepository;

    @Test
    void secondRequestDoesNotCallExternalApiAgain() throws Exception {
        GoogleCityDto cityDto = new GoogleCityDto(
                "place1",
                new DisplayName("Amsterdam", "en"),
                new Location(52.3676, 4.9041));

        GoogleCityResponseDto cityResponse = mock(GoogleCityResponseDto.class);
        when(cityResponse.getPlaces()).thenReturn(List.of(cityDto));

        GooglePoiDto sightDto = new GooglePoiDto("poi1",
                new DisplayName("Anne Frank House", "en"),
                "Westermarkt 20, Amsterdam",
                "https://example.com");


        GooglePoiResponseDto sightsResponse = mock(GooglePoiResponseDto.class);
        when(sightsResponse.getPlaces()).thenReturn(List.of(sightDto));

        when(placesSearchRepository.searchCity(eq("Amsterdam"))).thenReturn(cityResponse);
        when(placesSearchRepository.searchSights(any(City.class))).thenReturn(sightsResponse);

        mvc.perform(get("/sights").param("destinationName", "Amsterdam"))
                .andExpect(status().isOk());

        mvc.perform(get("/sights").param("destinationName", "Amsterdam"))
                .andExpect(status().isOk());

        verify(placesSearchRepository, times(1)).searchCity(eq("Amsterdam"));
        verify(placesSearchRepository, times(1)).searchSights(any(City.class));
    }
}
