package com.codecool.travelplanner.repository.places.sql;

import com.codecool.travelplanner.model.entity.sight.SightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightRepository extends JpaRepository<SightEntity, Long> {
    List<SightEntity> findByCityName(String cityName);
}
