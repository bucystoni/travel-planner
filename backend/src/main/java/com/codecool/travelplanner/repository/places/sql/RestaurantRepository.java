package com.codecool.travelplanner.repository.places.sql;

import com.codecool.travelplanner.model.entity.places.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    List<RestaurantEntity> findByCityName(String cityName);
}
