package com.example.travelagency.service;

import com.example.travelagency.exception.ApiExceptionHandler;
import com.example.travelagency.model.LocationModel;
import com.example.travelagency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public LocationModel addLocation(LocationModel locationToAdd) {
        return locationRepository.save(locationToAdd);
    }

    public List<LocationModel> getLocationList() {
        return locationRepository.findAll();
    }

    public List<LocationModel> getLocationListByContinent(String continent) {
        return locationRepository
                .findAllByContinent(continent);
    }

    public List<LocationModel> getLocationListByCountry(String country) {
        return locationRepository.findAllByCountry(country);
    }

    public LocationModel getLocationByCity(String city) {
        return locationRepository.findLocationByCity(city);
    }

    public LocationModel saveEditLocation(LocationModel locationToEdit) {
        return locationRepository.save(locationToEdit);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public LocationModel findById(Long locationId) throws ApiExceptionHandler {
        return locationRepository.findById(locationId).orElseThrow(ApiExceptionHandler::new);
    }
}
