package com.codecool.travelplanner.repository.trip;

import com.codecool.travelplanner.model.entity.trip.TripEntity;
import com.codecool.travelplanner.model.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findByUser(UserEntity user);

    Optional<TripEntity> findByIdAndUser(Long id, UserEntity user);
}
