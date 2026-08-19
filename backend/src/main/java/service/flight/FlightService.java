package service.flight;

import com.codecool.travelplanner.mapper.flight.FlightMapper;
import com.codecool.travelplanner.model.FlightOfferDto;
import com.codecool.travelplanner.repository.flight.FlightDataProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    private final FlightDataProvider flightDataProvider;
    private final FlightMapper flightMapper;

    public FlightService(FlightDataProvider flightDataProvider, FlightMapper flightMapper) {
        this.flightDataProvider = flightDataProvider;
        this.flightMapper = flightMapper;
    }

    public List<FlightOfferDto> getFlightOffers(String origin, String destination, LocalDate date) {
        return flightMapper.toFlightOffers(flightDataProvider.getFlightOffers(origin, destination, date));
    }

}
