package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.TripsApi;
import com.codecool.travelplanner.model.Trip;
import com.codecool.travelplanner.model.TripRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TripsController implements TripsApi {

    @Override
    public ResponseEntity<List<Trip>> tripsGet() {
        Trip trip1 = new Trip();
        trip1.setId((long) 1);
        trip1.setDestination("Paris");
        trip1.setDepartureDate(LocalDate.of(2026, 7, 29));
        trip1.setReturnDate(LocalDate.of(2026, 8, 2));
        trip1.setFlightTicket(null);
        trip1.setRestaurant(List.of());
        trip1.setAccommodation(List.of());
        trip1.setSight(List.of());

        Trip trip2 = new Trip();
        trip2.setId((long) 2);
        trip2.setDestination("London");
        trip2.setDepartureDate(LocalDate.of(2026, 7, 29));
        trip2.setReturnDate(LocalDate.of(2026, 8, 2));
        trip2.setFlightTicket(null);
        trip2.setRestaurant(List.of());
        trip2.setAccommodation(List.of());
        trip2.setSight(List.of());

        List<Trip> trips = List.of(trip1, trip2);

        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Trip> tripsPost(TripRequest request) {
        Trip trip = new Trip();
        trip.setId((long) 3);
        trip.setDestination(request.getDestination());
        trip.setDepartureDate(request.getDepartureDate());
        trip.setReturnDate(request.getReturnDate());
        trip.setFlightTicket(request.getFlightTicket());
        trip.setAccommodation(request.getAccommodation());
        trip.setSight(request.getSight());
        trip.setRestaurant(request.getRestaurant());

        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Trip> tripsIdGet(Long id) {
        Trip trip = new Trip();
        trip.setId(id);
        trip.setDestination("Paris");
        trip.setDepartureDate(LocalDate.of(2026, 7, 29));
        trip.setReturnDate(LocalDate.of(2026, 8, 2));
        trip.setFlightTicket(null);
        trip.setAccommodation(List.of());
        trip.setSight(List.of());
        trip.setRestaurant(List.of());

        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Trip> tripsIdPut(Long id, TripRequest request) {
        Trip trip = new Trip();
        trip.setId(id);
        trip.setDestination(request.getDestination());
        trip.setDepartureDate(request.getDepartureDate());
        trip.setReturnDate(request.getReturnDate());
        trip.setFlightTicket(request.getFlightTicket());
        trip.setAccommodation(request.getAccommodation());
        trip.setSight(request.getSight());
        trip.setRestaurant(request.getRestaurant());

        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> tripsIdDelete(Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}