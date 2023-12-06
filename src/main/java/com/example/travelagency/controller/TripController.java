package com.example.travelagency.controller;

import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.AirportService;
import com.example.travelagency.service.LocationService;
import com.example.travelagency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final LocationService locationService;
    private final AirportService airportService;

    @Autowired
    public TripController(TripService tripService, LocationService locationService, AirportService airportService) {
        this.tripService = tripService;
        this.locationService = locationService;
        this.airportService = airportService;
    }

    @GetMapping()
    public String getTripList(Model model) {
        List<TripModel> tripList = tripService.getTripList();
        model.addAttribute("trips", tripList);
        return "trips";
    }

    @GetMapping("continent/{continent}")
    public String getTripList(@PathVariable(value = "continent") String continent, Model model) {
        List<TripModel> tripsByContinent = tripService.getTripsByContinent(continent);
        model.addAttribute("continent/{continent}", tripsByContinent);
        return "trips";
    }

    @GetMapping("/addTrip")
    public String getAddTripForm(Model model) {
        TripModel tripModel = new TripModel();
        model.addAttribute("trip", tripModel);
        Set<String> continentList = locationService.getListOfContinents();
        model.addAttribute("continents", continentList);
        Set<String> countryList = locationService.getListOfCountires();
        model.addAttribute("countries", countryList);
        Set<String> airportList = airportService.getAirportModelNames();
        model.addAttribute("airports", airportList);
        return "addTrip";
    }

    @PostMapping("/addTrip")
    public String saveTrip(@ModelAttribute TripModel tripModel, Model model) {
        model.addAttribute("tripModel", tripModel);
        tripService.PostAddTrip(tripModel);
        return "getTrips";
    }


}
