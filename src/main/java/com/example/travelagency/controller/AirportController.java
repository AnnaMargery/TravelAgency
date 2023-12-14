package com.example.travelagency.controller;

import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.AirportModel;
import com.example.travelagency.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/airports", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<AirportModel>> getAirportsList() {
        List<AirportModel> airportsList = airportService.getAirportList();
        return ResponseEntity.ok(airportsList);
    }

    @GetMapping("/{airportId}")
    public ResponseEntity<AirportModel> getAirportById(@PathVariable Long airportId) {
        AirportModel airportById = airportService.getAirportById(airportId);
        return ResponseEntity.ok(airportById);
    }

    @PostMapping()
    public ResponseEntity<AirportModel> addAirport(@RequestBody AirportModel airportToAdd, AddressModel airportAddress) {
        AirportModel airport = airportService.addAirport(airportToAdd, airportAddress);
        return ResponseEntity.ok(airport);
    }

    @DeleteMapping("/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long airportId) {
        airportService.deleteAirport(airportId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<AirportModel> saveEditAirport(@RequestBody AirportModel airportToUpdate) {
        AirportModel updatedAirport = airportService.saveEditAirport(airportToUpdate);
        return ResponseEntity.ok(updatedAirport);
    }
}
