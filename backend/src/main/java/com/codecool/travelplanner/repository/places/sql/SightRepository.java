package com.codecool.travelplanner.repository.places.sql;

import com.codecool.travelplanner.model.entity.places.SightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SightRepository extends JpaRepository<SightEntity, Long> {
    List<SightEntity> findByCityName(String cityName);
}
