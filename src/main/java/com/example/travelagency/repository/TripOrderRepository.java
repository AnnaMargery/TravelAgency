package com.example.travelagency.repository;


import com.example.travelagency.model.TripOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripOrderRepository extends JpaRepository<TripOrderModel, Long> {

    TripOrderModel getTripOrderModelById(Long id);

    List<TripOrderModel> getAllByTripId(Long tripId);

    @Query("SELECT t FROM TripOrderModel t ORDER BY t.id DESC LIMIT 5")
    List<TripOrderModel> findFiveLastOrders();
}
