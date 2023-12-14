package com.example.travelagency.service;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.repository.TripOrderRepository;
import com.example.travelagency.repository.TripParticipantRepository;
import com.example.travelagency.repository.TripRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PreUpdate;
import org.hibernate.validator.constraints.time.DurationMax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
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

    public List<FoodOption> getFoodsList() {
        return Arrays.asList(FoodOption.values());
    }

    public TripModel getTripById(Long tripId) {
        if (tripRepository.existsById(tripId)) {
            return tripRepository.findById(tripId).get();
        }
        throw new ApiRequestException("Trip not found for id: " + tripId);
    }

    public List<TripModel> getPromotedTrips() {
        List<TripModel> promotedTrips = tripRepository.findByPromotedIsTrue();
        if (!promotedTrips.isEmpty()) {
            return promotedTrips;
        }
        throw new ApiRequestException("Promoted trips not found");
    }


    public void PostAddTrip(TripModel tripModel) {
        Long durationInMillies = Math.abs(tripModel.getEndDate().getTime() - tripModel.getStartDate().getTime());
        Long durationInDays = TimeUnit.DAYS.convert(durationInMillies, TimeUnit.MILLISECONDS);
        tripModel.setDuration(durationInDays);
        tripRepository.save(tripModel);
    }

    public TripModel saveEditTrip(TripModel tripToEdit) {
        Long durationInMillies = Math.abs(tripToEdit.getEndDate().getTime() - tripToEdit.getStartDate().getTime());
        Long durationInDays = TimeUnit.DAYS.convert(durationInMillies, TimeUnit.MILLISECONDS);
        tripToEdit.setDuration(durationInDays);
        return tripRepository.save(tripToEdit);
    }

    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new ApiRequestException("Trip not found for id: " + tripId);
        }
        tripRepository.deleteById(tripId);

    }

    public List<TripModel> getLastMinuteTrips() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        date = c.getTime();
        return tripRepository.findTripModelByStartDateBefore(date);
    }

    public List<TripModel> findSelectedTrips(Integer hotelStandard, FoodOption foodOption, String continent, String country) {
        if (!tripRepository.findSelectedTrip(hotelStandard, foodOption, continent, country).isEmpty()) {
            return tripRepository.findSelectedTrip(hotelStandard, foodOption, continent, country);
        }
        throw new ApiRequestException("Trip not found for requested criteria");
    }

    public List<TripModel> findByFoodOption(FoodOption foodOption) {
        return tripRepository.findTripModelByFoodOption(foodOption);
    }
}

