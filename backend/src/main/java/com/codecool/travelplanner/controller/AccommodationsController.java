package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.AccommodationsApi;
import com.codecool.travelplanner.model.PointOfInterest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccommodationsController implements AccommodationsApi {

    @Override
    public ResponseEntity<List<PointOfInterest>> accommodationsGet(String destinationName) {
        PointOfInterest acc1 = new PointOfInterest();
        acc1.setName("Avalon Resort & Spa");
        acc1.setAddress("3519 Miskolctapolca, Iglói út 15.");
        acc1.setUrl("https://avalonresort.hu/");

        PointOfInterest acc2 = new PointOfInterest();
        acc2.setName("Greenfield Hotel Golf & Spa");
        acc2.setAddress("9737  Bük, Golf út 4.");
        acc2.setUrl("https://greenfield.adventorhotels.hu/");

        List<PointOfInterest> dummyAccommodations = List.of(acc1, acc2);

        return new ResponseEntity<>(dummyAccommodations, HttpStatus.OK);
    }
}
