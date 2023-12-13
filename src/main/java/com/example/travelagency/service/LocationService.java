package com.example.travelagency.service;

import com.example.travelagency.exception.ApiInputException;
import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.LocationModel;
import com.example.travelagency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationModel addLocation(LocationModel locationToAdd) {
        List<LocationModel> locationList = locationRepository.findAll();
        for (LocationModel location : locationList) {
            if (location.getCity().equals(locationToAdd.getCity())) {
                throw new ApiInputException("Location already exists");
            }
        }
        return locationRepository.save(locationToAdd);
    }


    public List<LocationModel> getLocationList() {
        if (!locationRepository.findAll().isEmpty()) {
            return locationRepository.findAll();
        }
        throw new ApiRequestException("Locations not found");

    }

    public Set<String> getListOfContinents() {
        List<LocationModel> allLocations = locationRepository.findAll();
        Set<String> continents = new HashSet<>();
        for (LocationModel location : allLocations) {
            continents.add(location.getContinent());
        }
        return continents;
    }

    public Set<String> getListOfCountries() {
        List<LocationModel> allLocations = locationRepository.findAll();
        Set<String> countries = new HashSet<>();
        for (LocationModel location : allLocations) {
            countries.add(location.getCountry());
        }
        return countries;
    }

    public List<LocationModel> getLocationListByContinent(String continent) {
        if (!locationRepository.findAllLocationsByContinent(continent).isEmpty()) {
            return locationRepository.findAllLocationsByContinent(continent);
        }
        throw new ApiRequestException("Location not found for continent: " + continent);
    }

    public List<LocationModel> getLocationListByCountry(String country) {
        if (!locationRepository.findAllLocationsByCountry(country).isEmpty()) {
            return locationRepository.findAllLocationsByCountry(country);
        }
        throw new ApiRequestException("Location not found for country: " + country);
    }

    public LocationModel getLocationByCity(String city) {
        List<LocationModel> allLocations = locationRepository.findAll();
        for (LocationModel location : allLocations) {
            if (!location.getCity().equals(city)) {
                throw new ApiInputException("Location not found for city: " + city);
            }
        }
        return locationRepository.findLocationByCity(city);
    }

    public LocationModel saveEditLocation(LocationModel locationToEdit) {
        if (locationRepository.existsById(locationToEdit.getId())) {
            return locationRepository.save(locationToEdit);
        }
        throw new ApiInputException("Location not found for id: " + locationToEdit);
    }

    public void deleteLocation(Long locationId) {
        if (locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
        }
        throw new ApiInputException("Location nof found for id: " + locationId);
    }

    public LocationModel findById(Long locationId) {
        if (!locationRepository.existsById(locationId)) {
            throw new ApiRequestException("Location not found for id: " + locationId);
        }
        return locationRepository.findById(locationId).get();
    }
}

