package com.codecool.travelplanner.repository.places.sql;

import com.codecool.travelplanner.model.entity.places.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {
   Optional<List<AccommodationEntity>> findByCityName(String cityName);
}
