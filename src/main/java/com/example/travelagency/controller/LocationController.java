package com.example.travelagency.controller;

import com.example.travelagency.model.LocationModel;
import com.example.travelagency.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/{continent}")
    public ResponseEntity<List<LocationModel>> getLocationsByContinent(@PathVariable String continent) {
        try {
           List<LocationModel> locationList = locationService.getLocationListByContinent(continent);
            return ResponseEntity.ok(locationList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
