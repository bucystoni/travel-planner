package com.codecool.travelplanner.controller;

import com.codecool.travelplanner.api.RestaurantsApi;
import com.codecool.travelplanner.model.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantsController implements RestaurantsApi {

    @Override
    public ResponseEntity<List<Restaurant>> restaurantsGet(String destinationName) {
        Restaurant rest1 = new Restaurant();
        rest1.setId("1");
        rest1.setName("Cukorborsó Kertvendéglő");
        rest1.setAddress("2071 Páty, Somogyi Béla út 72.");
        rest1.setWebsiteUri("https://cukorborso.foodbuddy.hu/");

        Restaurant rest2 = new Restaurant();
        rest2.setId("2");
        rest2.setName("Náncsi Néni Vendéglője");
        rest2.setAddress("1029 Budapest, Ördögárok út 80.");
        rest2.setWebsiteUri("https://nancsineni.hu/");

        List<Restaurant> dummyRestaurants = List.of(rest1, rest2);

        return new ResponseEntity<>(dummyRestaurants, HttpStatus.OK);
    }
}
