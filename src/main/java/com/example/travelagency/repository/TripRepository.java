package com.example.travelagency.repository;

import com.example.travelagency.model.AirportModel;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripModel,Long> {
    List<TripModel> findAll();
//   List<TripModel> findAllByContinent(String continent);
/*
    List<TripModel> findAllByCountry(String country);

    List<TripModel> findAllByCity(String city);

    List<TripModel> findAllByHotel(HotelModel hotel);

    List<TripModel> findAllByAirportFrom(AirportModel airport);

    List<TripModel> findAllByAirportTo(AirportModel airport);

    List<TripModel> findAllByHotelStandard(Integer standard);

    List<TripModel> findAllByDuration(Integer duration);

    List<TripModel> findAllByPromoted(Boolean isPromoted);

    List<TripModel> findAllByStartDateIsLessThanEqual(LocalDate startDate);*/
}
