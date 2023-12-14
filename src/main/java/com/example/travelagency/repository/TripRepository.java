package com.example.travelagency.repository;

import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<TripModel> findTripModelByFoodOption(FoodOption FoodOption);

    List<TripModel> findTripModelByStartDateBefore(Date next7Days);

    @Query("SELECT t FROM TripModel t WHERE t.isPromoted= TRUE")
    List<TripModel> findByPromotedIsTrue();

    @Query("SELECT t FROM TripModel t " +
            "JOIN HotelModel h ON t.hotel.id = h.id " +
            "JOIN AddressModel a ON h.address.id= a.id " +
            "JOIN LocationModel l ON a.location.id = l.id " +
            "WHERE h.standard = ?1 AND t.foodOption = ?2 AND l.continent = ?3 AND l.country = ?4")
    List<TripModel> findSelectedTrip(Integer hotelStandard, FoodOption foodOption, String continent, String country);

}
