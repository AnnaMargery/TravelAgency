package com.example.travelagency.repository;

import com.example.travelagency.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<TripModel, Long> {

    List<TripModel> findAll();
    List<TripModel> findTripModelByHotelAddressLocationContinent(String continent);
    List<TripModel> findTripModelByHotelAddressLocationCountry(String country);
    Optional<TripModel> findById(Long id);

    List<TripModel> findTripModelByStartDateBefore(Date next7Days);

    @Query("SELECT t FROM TripModel t WHERE t.isPromoted= TRUE")
    List<TripModel> findByPromotedIsTrue();




}
