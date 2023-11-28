package com.example.travelagency.controller;

import com.example.travelagency.model.LocationModel;
import com.example.travelagency.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/locations", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationModel>> getLocationList() {
        List<LocationModel> locationList = locationService.getLocationList();
        return ResponseEntity.ok(locationList);
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<LocationModel>> getLocationsByContinent(@PathVariable String continent) {
        try {
            List<LocationModel> locationList = locationService.getLocationListByContinent(continent);
            return ResponseEntity.ok(locationList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<LocationModel>> getLocationsByCountry(@PathVariable String country) {
        try {
            List<LocationModel> locationModelList = locationService.getLocationListByCountry(country);
            return ResponseEntity.ok(locationModelList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<LocationModel>> getLocationsByCity(@PathVariable String city) {
        try {
            List<LocationModel> locationModelList = locationService.getLocationListByCity(city);
            return ResponseEntity.ok(locationModelList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // NIE MOGE JEJ PRZETESTOWAC, BO PRZEZ TO SECURITY NIE MOGE OBEJSC POSTMANA.
    @PostMapping
    public ResponseEntity<LocationModel> addLocation(@RequestBody LocationModel locationToAdd){
        try {
            LocationModel location = locationService.addLocation(locationToAdd);
            return ResponseEntity.ok(location);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //TODO to implement: methods:  delete/ edit
}
