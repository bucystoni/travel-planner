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
        UserEntity user = getUser();
        List<Trip> trips = tripService.getTrips(user);

        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Trip> tripsPost(TripRequest request) {
        UserEntity user = getUser();
        Trip trip = tripService.createTrip(request, user);

        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Trip> tripsIdGet(Long id) {
        UserEntity user = getUser();
        Trip trip = tripService.getTrip(id, user);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Trip> tripsIdPut(Long id, TripRequest request) {
        UserEntity user = getUser();
        Trip trip = tripService.updateTrip(id, request, user);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> tripsIdDelete(Long id) {
        UserEntity user = getUser();
        tripService.deleteTrip(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserEntity getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}