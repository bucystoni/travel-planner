package com.codecool.travelplanner.model.entity.flight;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "flight_segments")
public class FlightSegmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carrier;

    @Column(name = "flight_number")
    private String flightNumber;

    private String aircraft;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @Column(name = "departure_time")
    private OffsetDateTime departureTime;

    @Column(name = "arrival_time")
    private OffsetDateTime arrivalTime;

    @Column(name = "duration_minutes")
    private int durationMinutes;

    @ManyToOne
    @JoinColumn(name = "flight_offer_id")
    private FlightOfferEntity flightOffer;

    public FlightSegmentEntity() {
    }

    public FlightSegmentEntity(
            String carrier,
            String flightNumber,
            String aircraft,
            String departureAirport,
            String arrivalAirport,
            OffsetDateTime departureTime,
            OffsetDateTime arrivalTime,
            int durationMinutes) {
        this.carrier = carrier;
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.durationMinutes = durationMinutes;
    }

    public void setFlightOffer(FlightOfferEntity flightOffer) {
        this.flightOffer = flightOffer;
    }
}