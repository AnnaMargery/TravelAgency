package com.example.travelagency.service;

import com.example.travelagency.model.LocationModel;
import com.example.travelagency.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    // todo dto?

    public void addLocation(LocationModel locationToAdd) {
        locationRepository.save(locationToAdd);
    }

    public List<LocationModel> getLocationList(){
        return locationRepository.findAll();
    }

    public List<LocationModel> getLocationListByContinent(String continent) {
        return locationRepository
                .findAllByContinent(continent);
    }

    public List<LocationModel> getLocationListByCountry(String country) {
        return locationRepository.findAllByCountry(country);
    }

    public List<LocationModel> getLocationListByCity(String city) {
        return locationRepository.findAllByCity(city);
    }

    public void saveEditLocation(LocationModel locationToEdit) {
        locationRepository.save(locationToEdit);
    }

    public void deleteLocation(LocationModel locationToDelete) {
        locationRepository.save(locationToDelete);
    }

}
