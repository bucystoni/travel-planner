package com.codecool.travelplanner.service.flight;

import com.codecool.travelplanner.mapper.flight.FlightMapper;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.repository.flight.FlightDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightDataProvider flightDataProvider;

    @Mock
    private FlightMapper flightMapper;

    @Test
    void getFlightOffersDelegatesToProviderAndMapper() {
        FlightService service = new FlightService(flightDataProvider, flightMapper);

        LocalDate date = LocalDate.of(2026, 8, 27);
        List<FlightOfferEntity> entities = List.of(
                new FlightOfferEntity("BUD", "LON", date, 199.99, "EUR", "Economy", false, 180)
        );
        List<FlightOfferDto> expectedDtos = List.of(new FlightOfferDto());

        when(flightDataProvider.getFlightOffers("BUD", "LON", date)).thenReturn(entities);
        when(flightMapper.toFlightOffers(entities)).thenReturn(expectedDtos);

        List<FlightOfferDto> result = service.getFlightOffers("BUD", "LON", date);

        assertThat(result).isEqualTo(expectedDtos);
        verify(flightDataProvider).getFlightOffers("BUD", "LON", date);
        verify(flightMapper).toFlightOffers(entities);
    }
}
