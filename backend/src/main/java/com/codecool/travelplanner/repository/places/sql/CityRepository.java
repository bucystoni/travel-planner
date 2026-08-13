package com.codecool.travelplanner.repository.places.sql;

import com.codecool.travelplanner.model.entity.places.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    Optional<List<CityEntity>> findByName (String name);
}
