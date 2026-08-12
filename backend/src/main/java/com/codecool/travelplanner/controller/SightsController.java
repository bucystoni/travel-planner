package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.SightsApi;
import com.codecool.travelplanner.model.PointOfInterest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SightsController implements SightsApi {

    @Override
    public ResponseEntity<List<PointOfInterest>> sightsGet(String destinationName) {
        PointOfInterest sight1 = new PointOfInterest();
        sight1.setName("Eiffel Tower");
        sight1.setAddress("Champ de Mars, 5 Avenue Anatole France, 75007 Paris");
        sight1.setUrl("https://www.toureiffel.paris");

        PointOfInterest sight2 = new PointOfInterest();
        sight2.setName("Louvre Museum");
        sight2.setAddress("Rue de Rivoli, 75001 Paris");
        sight2.setUrl("https://www.louvre.fr");

        List<PointOfInterest> dummySights = List.of(sight1, sight2);

        return new ResponseEntity<>(dummySights, HttpStatus.OK);
    }
}
