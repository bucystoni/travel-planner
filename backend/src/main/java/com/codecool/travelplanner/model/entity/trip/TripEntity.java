package com.codecool.travelplanner.model.entity.trip;

import com.codecool.travelplanner.model.entity.flight.FlightOfferEntity;
import com.codecool.travelplanner.model.entity.places.CityEntity;
import com.codecool.travelplanner.model.entity.places.RestaurantEntity;
import com.codecool.travelplanner.model.entity.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trips")
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;
    @ManyToOne
    @JoinColumn(name = "flight_offer_id")
    private FlightOfferEntity flightOffer;
    @ManyToMany
    @JoinTable(
            name = "trip_restaurants",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<RestaurantEntity> restaurants = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "trip_accommodations",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "accommodation_id")
    )
    private List<RestaurantEntity> accommodations = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "trip_sights",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "sight_id")
    )
    private List<RestaurantEntity> sights = new ArrayList<>();


    public TripEntity() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public FlightOfferEntity getFlightOffer() {
        return flightOffer;
    }

    public void setFlightOffer(FlightOfferEntity flightOffer) {
        this.flightOffer = flightOffer;
    }

    public List<RestaurantEntity> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<RestaurantEntity> accommodations) {
        this.accommodations = accommodations;
    }

    public List<RestaurantEntity> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantEntity> restaurants) {
        this.restaurants = restaurants;
    }

    public List<RestaurantEntity> getSights() {
        return sights;
    }

    public void setSights(List<RestaurantEntity> sights) {
        this.sights = sights;
    }
}
