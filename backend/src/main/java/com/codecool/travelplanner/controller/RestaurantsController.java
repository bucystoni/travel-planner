package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.RestaurantsApi;
import com.codecool.travelplanner.model.PointOfInterest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantsController implements RestaurantsApi {

    @Override
    public ResponseEntity<List<PointOfInterest>> restaurantsGet(String destinationName) {
        PointOfInterest rest1 = new PointOfInterest();
        rest1.setName("Cukorborsó Kertvendéglő");
        rest1.setAddress("2071 Páty, Somogyi Béla út 72.");
        rest1.setUrl("https://cukorborso.foodbuddy.hu/");

        PointOfInterest rest2 = new PointOfInterest();
        rest2.setName("Náncsi Néni Vendéglője");
        rest2.setAddress("1029 Budapest, Ördögárok út 80.");
        rest2.setUrl("https://nancsineni.hu/");

        List<PointOfInterest> dummyRestaurants = List.of(rest1, rest2);

        return new ResponseEntity<>(dummyRestaurants, HttpStatus.OK);
    }
}
