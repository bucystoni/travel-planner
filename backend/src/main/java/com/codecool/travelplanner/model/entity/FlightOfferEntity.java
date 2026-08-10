package com.codecool.travelplanner.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    @OneToMany(mappedBy = "flightOffer")
    private List<FlightSegmentEntity> segments;

    public FlightOfferEntity() {}

    public FlightOfferEntity(
            String origin,
            String destination,
            LocalDate departureDate,
            double price,
            String currency,
            String cabinClass,
            boolean requiresSelfTransfer,
            int totalDurationMinutes,
            List<FlightSegmentEntity> segments) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price = price;
        this.currency = currency;
        this.cabinClass = cabinClass;
        this.requiresSelfTransfer = requiresSelfTransfer;
        this.totalDurationMinutes = totalDurationMinutes;
        this.segments = segments;
    }
}
