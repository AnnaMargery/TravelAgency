package com.example.travelagency.repository;

import com.example.travelagency.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<TripModel, Long> {

    List<TripModel> findAll();

    List<TripModel> findTripModelByHotelAddressLocationContinent(String continent);

    List<TripModel> findTripModelByHotelAddressLocationCountry(String country);
//
//    List<TripModel> findTripModelByAirportFromAddress(String addressOfDeparture);
//
//    List<TripModel> findTripModelByHotelAddressLocationCity(String city);
//
//    List<TripModel> findTripModelByFoodOption(String foodOption);
//
//    List<TripModel> findTripModelByHotelStandard(Integer hotelStandard);
//
//    List<TripModel> findTripModelByDuration(Integer durationOfTrip);
//
//    List<TripModel> findTripModelByPromotedTrue();

//    List<TripModel> findTripModelByPriceForAdultOOrderByPriceForAdultAsc();
//    List<TripModel> findTripModelByPriceForAdultOOrderByPriceForAdultDesc();

    // todo hmmm nie wim jak podejsc do zbloizajacych sie wycieczek...
//    List<TripModel> findAllByStartDateIsNear();

    Optional<TripModel> findById(Long id);


}
