package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.AccommodationsApi;
import com.codecool.travelplanner.model.Accommodation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccommodationsController implements AccommodationsApi {

    @Override
    public ResponseEntity<List<Accommodation>> accommodationsGet(String destinationName) {
        Accommodation acc1 = new Accommodation();
        acc1.setId("1");
        acc1.setName("Avalon Resort & Spa");
        acc1.setAddress("3519 Miskolctapolca, Iglói út 15.");
        acc1.setWebsiteUri("https://avalonresort.hu/");

        Accommodation acc2 = new Accommodation();
        acc2.setId("2");
        acc2.setName("Greenfield Hotel Golf & Spa");
        acc2.setAddress("9737  Bük, Golf út 4.");
        acc2.setWebsiteUri("https://greenfield.adventorhotels.hu/");

        List<Accommodation> dummyAccommodations = List.of(acc1, acc2);

        return new ResponseEntity<>(dummyAccommodations, HttpStatus.OK);
    }
}
