package com.example.travelagency.service;


import com.example.travelagency.model.TripModel;
import com.example.travelagency.repository.LocationRepository;
import com.example.travelagency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public TripService(TripRepository tripRepository, LocationRepository locationRepository) {
        this.tripRepository = tripRepository;
        this.locationRepository = locationRepository;
    }

    /*public TripModel addTrip(TripModel tripToAdd) {
        return tripRepository.save(tripToAdd);
    }*/

    public List<TripModel> getTripList() {
        return tripRepository.findAll();
    }

/*
    public List<TripModel> getTripListByContinent(String continent){
    locationRepository.findAllByContinent(continent);
        return tripRepository.findAllByContinent(continent);
    }*/
/*
    public List<TripModel> getTripListByCountry(String country){
        return tripRepository.findAllByCountry(country);
    }

    public List<TripModel> getTripListByCity(String city){
        return tripRepository.findAllByCity(city);
    }
    public List<TripModel> getTripListByHotel(HotelModel hotel){
        return tripRepository.findAllByHotel(hotel);
    }

    public List<TripModel> getTripListByAirportFrom(AirportModel airport){
        return tripRepository.findAllByAirportFrom(airport);
    }

    public List<TripModel> getTripListByAirportTo(AirportModel airport){
        return tripRepository.findAllByAirportTo(airport);
    }

    public List<TripModel> getTripListByHotelStandard(Integer standard){
        return tripRepository.findAllByHotelStandard(standard);
    }

    public List<TripModel> getTripListByDuration(Integer duration){
        return tripRepository.findAllByDuration(duration);
    }

    public List<TripModel> getTripListPromoted(Boolean isPromoted){
        return tripRepository.findAllByPromoted(isPromoted);
    }


    //to nie jest dobrze do konca z data
    public List<TripModel> getTripListStartDayIsNextWeek(LocalDate startDate){
        return tripRepository.findAllByStartDateIsLessThanEqual(startDate);
    }

    public TripModel saveEditTrip(TripModel tripToEdit) {
        return tripRepository.save(tripToEdit);*/
  /*  }




    public void deleteTrip(TripModel tripToDelete) {
        tripRepository.save(tripToDelete);
    }
*/

}

