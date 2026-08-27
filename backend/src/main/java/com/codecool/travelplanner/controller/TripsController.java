package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.TripsApi;
import com.codecool.travelplanner.model.Trip;
import com.codecool.travelplanner.model.TripRequest;
import com.codecool.travelplanner.model.entity.user.UserEntity;
import com.codecool.travelplanner.repository.user.UserRepository;
import com.codecool.travelplanner.service.trip.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TripsController implements TripsApi {
    private final TripService tripService;
    private final UserRepository userRepository;

    public TripsController(TripService tripService, UserRepository userRepository) {
        this.tripService = tripService;
        this.userRepository = userRepository;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Trip>> tripsGet() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        List<Trip> trips = tripService.getTrips(user);

        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Trip> tripsPost(TripRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Trip trip = tripService.createTrip(request, user);

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