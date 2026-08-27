package com.codecool.travelplanner.configuration;

import com.codecool.travelplanner.security.service.AuthService;
import com.codecool.travelplanner.service.flight.FlightDataImportService;
import com.codecool.travelplanner.service.places.PlacesDataImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FlightDataImportService flightDataImportService;
    private final PlacesDataImportService placesDataImportService;
    private final AuthService authService;

    public DataInitializer(FlightDataImportService flightDataImportService, PlacesDataImportService placesDataImportService, AuthService authService) {
        this.flightDataImportService = flightDataImportService;
        this.placesDataImportService = placesDataImportService;
        this.authService = authService;
    }

    @Override
    public void run(String... args) {
        if (!flightDataImportService.isDatabaseInitialized()) {
            flightDataImportService.importFlightData();
        }

        if (!placesDataImportService.isDatabaseInitialized()) {
            placesDataImportService.importPlacesData();
        }

        authService.createAdminIfNotExists();
    }
}