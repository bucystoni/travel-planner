package com.codecool.travelplanner.service.flight;

import com.codecool.travelplanner.dto.ignav.FlightResponseDto;
import com.codecool.travelplanner.mapper.flight.FlightEntityMapper;
import com.codecool.travelplanner.repository.flight.FlightOfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.flight.FlightDataImportService;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FlightDataImportServiceTest {

    @Mock
    private FlightOfferRepository repository;

    @Mock
    private FlightEntityMapper flightEntityMapper;

    private final JsonMapper objectMapper = JsonMapper.builder().build();

    @Test
    void getFlightOffersReadsBothMockFiles() {
        FlightDataImportService service =
                new FlightDataImportService(objectMapper, flightEntityMapper, repository);

        List<FlightResponseDto> offers = service.getFlightOffers();

        assertThat(offers).hasSize(2);

        assertThat(offers.get(0).origin()).isEqualTo("BUD");
        assertThat(offers.get(0).destination()).isEqualTo("LHR");
        assertThat(offers.get(0).itineraries()).hasSize(12);

        assertThat(offers.get(1).origin()).isEqualTo("BUD");
        assertThat(offers.get(1).destination()).isEqualTo("NRT");
        assertThat(offers.get(1).itineraries()).hasSize(11);
    }
}