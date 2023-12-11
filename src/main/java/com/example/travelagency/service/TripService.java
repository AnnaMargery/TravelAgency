package com.example.travelagency.service;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.repository.TripOrderRepository;
import com.example.travelagency.repository.TripParticipantRepository;
import com.example.travelagency.repository.TripRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PreUpdate;
import org.hibernate.validator.constraints.time.DurationMax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final TripOrderRepository tripOrderRepository;
    private final TripParticipantService tripParticipantService;

    @Autowired
    public TripService(TripRepository tripRepository, TripOrderRepository tripOrderRepository, TripParticipantService tripParticipantService) {
        this.tripRepository = tripRepository;
        this.tripOrderRepository = tripOrderRepository;
        this.tripParticipantService = tripParticipantService;
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

    public List<TripModel> getPromotedTrips(){
        List<TripModel> promotedTrips = new ArrayList<>();
        List<TripModel> allTrips = tripRepository.findAll();
        for (TripModel trip : allTrips) {
            if(trip.isPromoted()==true){
                promotedTrips.add(trip);
            }
        }
        if (!promotedTrips.isEmpty()) {
            return promotedTrips;
        }
        throw new ApiRequestException("Promoted trips not found");
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

    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new ApiRequestException("Trip not found for id: " + tripId);
        }
        tripRepository.deleteById(tripId);
    }

//todo sprawdzic implementacje
    public List<TripModel> getLastMinuteTrips() {
        LocalDate dateInTheFuture = LocalDate.now().plusDays(3);
        return tripRepository.findLastMinuteTrips();


    }
}

