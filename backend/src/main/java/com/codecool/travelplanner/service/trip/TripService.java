package com.codecool.travelplanner.service.trip;

import com.codecool.travelplanner.exception.InvalidTripReferenceException;
import com.codecool.travelplanner.exception.TripNotFoundException;
import com.codecool.travelplanner.mapper.trip.TripMapper;
import com.codecool.travelplanner.model.PointOfInterest;
import com.codecool.travelplanner.model.Trip;
import com.codecool.travelplanner.model.TripRequest;
import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.model.entity.places.AccommodationEntity;
import com.codecool.travelplanner.model.entity.places.RestaurantEntity;
import com.codecool.travelplanner.model.entity.places.SightEntity;
import com.codecool.travelplanner.model.entity.trip.TripEntity;
import com.codecool.travelplanner.model.entity.user.UserEntity;
import com.codecool.travelplanner.repository.flight.FlightOfferRepository;
import com.codecool.travelplanner.repository.places.sql.AccommodationRepository;
import com.codecool.travelplanner.repository.places.sql.RestaurantRepository;
import com.codecool.travelplanner.repository.places.sql.SightRepository;
import com.codecool.travelplanner.repository.trip.TripRepository;
import com.codecool.travelplanner.service.places.PlacesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final PlacesService placesService;
    private final AccommodationRepository accommodationRepository;
    private final RestaurantRepository restaurantRepository;
    private final SightRepository sightRepository;
    private final FlightOfferRepository flightRepository;


    public TripService(TripRepository tripRepository, TripMapper tripMapper, PlacesService placesService, AccommodationRepository accommodationRepository, RestaurantRepository restaurantRepository, SightRepository sightRepository, FlightOfferRepository flightRepository) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.placesService = placesService;
        this.accommodationRepository = accommodationRepository;
        this.restaurantRepository = restaurantRepository;
        this.sightRepository = sightRepository;
        this.flightRepository = flightRepository;
    }

    @Transactional(readOnly = true) // TODO: N+1
    public List<Trip> getTrips(UserEntity user) {
        return tripRepository.findByUser(user)
                .stream().map(tripMapper::toTrip).toList();
    }

    @Transactional
    public Trip createTrip(TripRequest request, UserEntity user) {
        TripEntity trip = new TripEntity();
        trip.setUser(user);
        applyRequest(trip, request);

        TripEntity saved = tripRepository.save(trip);
        return tripMapper.toTrip(saved);
    }

    @Transactional(readOnly = true)
    public Trip getTrip(Long id, UserEntity user) {
        return tripRepository.findByIdAndUser(id, user)
                .map(tripMapper::toTrip)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));
    }

    @Transactional
    public void deleteTrip(Long id, UserEntity user) {
        TripEntity trip = tripRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));
        tripRepository.delete(trip);
    }

    @Transactional
    public Trip updateTrip(Long id, TripRequest request, UserEntity user) {
        TripEntity trip = tripRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));
        applyRequest(trip, request);

        return tripMapper.toTrip(trip);
    }

    private void applyRequest(TripEntity trip, TripRequest request) {
        trip.setCity(placesService.searchCity(request.getDestination()));
        trip.setDepartureDate(request.getDepartureDate());
        trip.setReturnDate(request.getReturnDate());

        // flight
        FlightOfferEntity flightOffer = null;
        if (request.getFlightTicket() != null) {
            Long flightId = request.getFlightTicket().getId();
            flightOffer = flightRepository.findById(flightId)
                    .orElseThrow(() -> new InvalidTripReferenceException(flightId));
        }
        trip.setFlightOffer(flightOffer);

        //restaurants
        List<RestaurantEntity> restaurants = List.of();
        if (request.getRestaurant() != null) {
            List<Long> ids = request.getRestaurant().stream()
                    .map(PointOfInterest::getId)
                    .toList();
            restaurants = restaurantRepository.findAllById(ids);
            if (restaurants.size() != ids.size()) {
                throw new TripNotFoundException("Restaurant(s) not found");
            }
        }
        trip.setRestaurants(new ArrayList<>(restaurants));

        //accommodation
        List<AccommodationEntity> accommodations = List.of();
        if (request.getAccommodation() !=null){
            List<Long> ids = request.getAccommodation().stream()
                    .map(PointOfInterest::getId)
                    .toList();
            accommodations = accommodationRepository.findAllById(ids);
            if(accommodations.size() != ids.size()) {
                throw new TripNotFoundException("Accommodation(s) not found");
            }
        }
        trip.setAccommodations(new ArrayList<>(accommodations));

        //sights
        List<SightEntity> sights = List.of();
        if (request.getSight() !=null){
            List<Long> ids = request.getSight().stream()
                    .map(PointOfInterest::getId)
                    .toList();
            sights = sightRepository.findAllById(ids);
            if(sights.size() != ids.size()) {
                throw new TripNotFoundException("Sight(s) not found");
            }
        }
        trip.setSights(new ArrayList<>(sights));
    }
}
