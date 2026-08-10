package com.codecool.travelplanner.configuration;

import com.codecool.travelplanner.service.FlightDataImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FlightDataInitializer implements CommandLineRunner {

    private final FlightDataImportService flightDataImportService;

    public FlightDataInitializer(FlightDataImportService flightDataImportService) {
        this.flightDataImportService = flightDataImportService;
    }

    @Override
    public void run(String... args) {
        flightDataImportService.importFlightData();
    }
}