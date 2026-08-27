package com.codecool.travelplanner.mapper.trip;

import com.codecool.travelplanner.mapper.flight.FlightMapper;
import com.codecool.travelplanner.mapper.places.PlacesMapper;
import com.codecool.travelplanner.model.Trip;
import com.codecool.travelplanner.model.entity.trip.TripEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TripMapper {
    private FlightMapper flightMapper;
    private PlacesMapper placesMapper;


    public TripMapper(FlightMapper flightMapper, PlacesMapper placesMapper) {
        this.flightMapper = flightMapper;
        this.placesMapper = placesMapper;
    }

    public Trip toTrip(TripEntity tripEntity) {
        Trip trip = new Trip();
        trip.id(tripEntity.getId());
        trip.departureDate(tripEntity.getDepartureDate());
        trip.returnDate(tripEntity.getReturnDate());
        trip.destination(tripEntity.getCity().getName());
        if (tripEntity.getFlightOffer() != null) {
            trip.flightTicket(flightMapper.toFlightOffer(tripEntity.getFlightOffer()));
        }
        trip.setAccommodation(tripEntity.getAccommodations().stream()
                .map(placesMapper::convertPlaceEntityToPointOfInterest).toList());
        trip.setRestaurant(tripEntity.getRestaurants().stream()
                .map(placesMapper::convertPlaceEntityToPointOfInterest).toList());
        trip.setSight(tripEntity.getSights().stream()
                .map(placesMapper::convertPlaceEntityToPointOfInterest).toList());

        return trip;
    }
}
