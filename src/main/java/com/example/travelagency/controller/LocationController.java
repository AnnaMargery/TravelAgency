package com.example.travelagency.controller;

import com.example.travelagency.model.LocationModel;
import com.example.travelagency.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(value = "/locations", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationModel>> getLocationList() {
        try {
            List<LocationModel> locationList = locationService.getLocationList();
            return ResponseEntity.ok(locationList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<LocationModel>> getLocationsByContinent(@PathVariable("continent") String continent) {
        try {
            List<LocationModel> locationList = locationService.getLocationListByContinent(continent);
            return ResponseEntity.ok(locationList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<LocationModel>> getLocationsByCountry(@PathVariable("country") String country) {
        try {
            List<LocationModel> locationModelList = locationService.getLocationListByCountry(country);
            return ResponseEntity.ok(locationModelList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<LocationModel> getLocationsByCity(@PathVariable("city") String city) {
        try {
            LocationModel locationModelList = locationService.getLocationByCity(city);
            return ResponseEntity.ok(locationModelList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<LocationModel> addLocation(@RequestBody LocationModel locationToAdd) {
        try {
            LocationModel location = locationService.addLocation(locationToAdd);
            return ResponseEntity.ok(location);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<LocationModel> saveEditLocation(@RequestBody LocationModel locationToEdit) {
        try {
            LocationModel updateLocation = locationService.saveEditLocation(locationToEdit);
            return ResponseEntity.ok(updateLocation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
