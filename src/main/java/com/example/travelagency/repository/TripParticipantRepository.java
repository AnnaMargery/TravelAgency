package com.example.travelagency.repository;

import com.example.travelagency.model.TripModel;
import com.example.travelagency.model.TripParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripParticipantRepository extends JpaRepository<TripParticipantModel, Long> {
    List<TripParticipantModel> findAllByTripOrderId(Long tripOrderID);

    void deleteAllByTripOrderTrip(TripModel tripModel);
}
