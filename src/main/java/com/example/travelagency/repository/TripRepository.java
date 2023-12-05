package com.example.travelagency.repository;

import com.example.travelagency.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripModel, Long> {
    List<TripModel> findAll();
    List<TripModel> findTripModelByHotelAddressLocationContinent(String continent);

}
