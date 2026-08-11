package com.codecool.travelplanner.model.entity.flight;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flight_offers")
public class FlightOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;

    private String destination;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    private double price;

    private String currency;

    @Column(name = "cabin_class")
    private String cabinClass;

    @Column(name = "requires_self_transfer")
    private boolean requiresSelfTransfer;

    @Column(name = "total_duration_minutes")
    private int totalDurationMinutes;

    @OneToMany(
            mappedBy = "flightOffer",
            cascade = CascadeType.ALL
    )
    private List<FlightSegmentEntity> segments = new ArrayList<>();

    public FlightOfferEntity() {
    }

    public FlightOfferEntity(
            String origin,
            String destination,
            LocalDate departureDate,
            double price,
            String currency,
            String cabinClass,
            boolean requiresSelfTransfer,
            int totalDurationMinutes) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price = price;
        this.currency = currency;
        this.cabinClass = cabinClass;
        this.requiresSelfTransfer = requiresSelfTransfer;
        this.totalDurationMinutes = totalDurationMinutes;
    }

    public void addSegment(FlightSegmentEntity segment) {
        segments.add(segment);
        segment.setFlightOffer(this);
    }

    public Long getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public boolean isRequiresSelfTransfer() {
        return requiresSelfTransfer;
    }

    public int getTotalDurationMinutes() {
        return totalDurationMinutes;
    }

    public List<FlightSegmentEntity> getSegments() {
        return segments;
    }
}