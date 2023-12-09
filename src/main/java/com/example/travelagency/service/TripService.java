package com.example.travelagency.service;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.repository.TripRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PreUpdate;
import org.hibernate.validator.constraints.time.DurationMax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public TripModel getTripById(Long tripId) {
        if (tripRepository.existsById(tripId)) {
            return tripRepository.findById(tripId).get();
        }
        throw new ApiRequestException("Trip not found for id: " + tripId);
    }


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


    public void PostAddTrip(TripModel tripModel) {
//        TripModel tripModel = new TripModel();
        Long durationInMillies = Math.abs(tripModel.getEndDate().getTime() - tripModel.getStartDate().getTime());
        Long durationInDays = TimeUnit.DAYS.convert(durationInMillies, TimeUnit.MILLISECONDS);
        tripModel.setDuration(durationInDays);
        tripRepository.save(tripModel);
    }

    public TripModel saveEditTrip(TripModel tripToEdit) {
        return tripRepository.save(tripToEdit);
    }

    public void deleteTrip(TripModel tripToDelete) {
        tripRepository.save(tripToDelete);
    }


}

