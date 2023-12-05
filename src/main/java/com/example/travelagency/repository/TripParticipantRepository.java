package com.example.travelagency.repository;

import com.example.travelagency.model.TripParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripParticipantRepository extends JpaRepository<TripParticipantModel, Long> {
}
