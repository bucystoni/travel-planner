package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.SightsApi;
import com.codecool.travelplanner.model.Sight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SightsController implements SightsApi {

    @Override
    public ResponseEntity<List<Sight>> sightsGet(String destinationName) {
        Sight sight1 = new Sight();
        sight1.setId("1");
        sight1.setName("Eiffel Tower");
        sight1.setAddress("Champ de Mars, 5 Avenue Anatole France, 75007 Paris");
        sight1.setWebsiteUri("https://www.toureiffel.paris");

        Sight sight2 = new Sight();
        sight2.setId("2");
        sight2.setName("Louvre Museum");
        sight2.setAddress("Rue de Rivoli, 75001 Paris");
        sight2.setWebsiteUri("https://www.louvre.fr");

        List<Sight> dummySights = List.of(sight1, sight2);

        return new ResponseEntity<>(dummySights, HttpStatus.OK);
    }
}
