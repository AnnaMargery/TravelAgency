package com.example.travelagency.controller;

import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/trips", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public String getTripList(Model model){
        List<TripModel>tripList = tripService.getTripList();
        model.addAttribute("tripModel",tripList);
        return "/trips";
    }


/*
    @GetMapping("/trip/{trip}")
    public ResponseEntity<List<TripModel>> getTripListByContinent(@PathVariable("continent") String continent) {
        try {
            List<TripModel> trip = tripService.getTripListByContinent(continent);
            return ResponseEntity.ok(trip);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PostMapping("/addTrip")
    public ResponseEntity<TripModel> addTrip(@RequestBody TripModel tripToAdd) {
        try {
            TripModel trip = tripService.addTrip(tripToAdd);
            return ResponseEntity.ok(trip);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping
    public ResponseEntity<TripModel> saveEditTrip(@RequestBody TripModel tripToEdit) {
        try {
            TripModel updateTrip = tripService.saveEditTrip(tripToEdit);
            return ResponseEntity.ok(updateTrip);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
}
