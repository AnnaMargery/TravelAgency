package com.example.travelagency.controller;

import com.example.travelagency.model.LocationModel;
import com.example.travelagency.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<LocationModel> locationList = locationService.getLocationList();
        return ResponseEntity.ok(locationList);
    }

    //todo zapytać Mikolaja o exceptiony
    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<LocationModel>> getLocationsByContinent(@PathVariable("continent") String continent) {
        List<LocationModel> locationList = locationService.getLocationListByContinent(continent);
        return ResponseEntity.ok(locationList);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<LocationModel>> getLocationsByCountry(@PathVariable("country") String country) {
        List<LocationModel> locationModelList = locationService.getLocationListByCountry(country);
        return ResponseEntity.ok(locationModelList);

    }

    @GetMapping("/city/{city}")
    public ResponseEntity<LocationModel> getLocationsByCity(@PathVariable("city") String city) {
        LocationModel locationModelList = locationService.getLocationByCity(city);
        return ResponseEntity.ok(locationModelList);
    }

    @PostMapping
    public ResponseEntity<LocationModel> addLocation(@RequestBody LocationModel locationToAdd) {
        LocationModel location = locationService.addLocation(locationToAdd);
        return ResponseEntity.ok(location);
    }

    @PutMapping
    public ResponseEntity<LocationModel> saveEditLocation(@RequestBody LocationModel locationToEdit) {
        LocationModel updateLocation = locationService.saveEditLocation(locationToEdit);
        return ResponseEntity.ok(updateLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
