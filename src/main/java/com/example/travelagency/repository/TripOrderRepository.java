package com.example.travelagency.repository;


import com.example.travelagency.model.TripOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripOrderRepository extends JpaRepository<TripOrderModel, Long> {

    TripOrderModel getTripOrderModelById(Long id);

    List<TripOrderModel> getAllByTripId(Long tripId);

}
