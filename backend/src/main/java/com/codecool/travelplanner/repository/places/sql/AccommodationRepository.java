package com.codecool.travelplanner.repository.places.sql;

import com.codecool.travelplanner.model.entity.accommodation.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {
    List<AccommodationEntity> findByCityName(String cityName);
}
