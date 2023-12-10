package com.example.travelagency.repository;


import com.example.travelagency.model.TripOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TripOrderRepository extends JpaRepository<TripOrderModel, Long> {

    TripOrderModel getTripOrderModelById(Long id);

    void deleteOrderByTripId(Long tripId);
}
