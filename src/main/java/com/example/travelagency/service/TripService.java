package com.example.travelagency.service;

import com.example.travelagency.model.TripModel;
import com.example.travelagency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<TripModel> getTripList() {
        return tripRepository.findAll();
    }

    public List<TripModel> getTripsByContinent(String continent) {
        return tripRepository.findTripModelByHotelAddressLocationContinent(continent);
    }

    public List<TripModel> getTripsByCountry(String country) {
        return tripRepository.findTripModelByHotelAddressLocationCountry(country);
    }
//
//    public List<TripModel> getTripsByCityOfDeparture(String cityOdDeparture) {
//        return tripRepository.findTripModelByAirportFromAddress(cityOdDeparture);
//    }
//
//    public List<TripModel> getTripsByHotelCity(String cityOfHotel) {
//        return tripRepository.findTripModelByHotelAddressLocationCity(cityOfHotel);
//    }
//
//    public List<TripModel> getTripsByFoodOption(String foodOption) {
//        return tripRepository.findTripModelByFoodOption(foodOption);
//    }
//
//    public List<TripModel> getTRipsByHotelStandard(Integer hotelStandard) {
//        return tripRepository.findTripModelByHotelStandard(hotelStandard);
//    }
//
//    public List<TripModel> findTripsByDuration(Integer durationOfTrip) {
//        return tripRepository.findTripModelByDuration(durationOfTrip);
//    }
//
//    public List<TripModel> findTripsPromoted() {
//        return tripRepository.findTripModelByPromotedTrue();
//    }
//
//    //todo nie jestem pewna
//    public List<TripModel> findTripsSortedByPriceForAdultAscendingOrder() {
//        return tripRepository.findTripModelByPriceForAdultOOrderByPriceForAdultAsc();
//    }
//
//    public List<TripModel> findTripsSortedByPriceForAdultDescendingOrder() {
//        return tripRepository.findTripModelByPriceForAdultOOrderByPriceForAdultDesc();
//    }
//
//    public List<TripModel> findTripsSortedByDepartureDate() {
//        return tripRepository.findAllByStartDateIsNear();
//    }
//
//    public void PostAddTrip(TripModel trip) {
//        tripRepository.save(trip);
//    }



    public void PostAddTrip(TripModel trip) {
        tripRepository.save(trip);
    }

    public TripModel saveEditTrip(TripModel tripToEdit) {
        return tripRepository.save(tripToEdit);
    }

    public void deleteTrip(TripModel tripToDelete) {
        tripRepository.save(tripToDelete);
    }


}

