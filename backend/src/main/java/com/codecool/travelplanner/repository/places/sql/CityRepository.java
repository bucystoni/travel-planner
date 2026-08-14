package com.codecool.travelplanner.repository.places.sql;

import com.codecool.travelplanner.model.entity.places.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findByName (String name);
}
