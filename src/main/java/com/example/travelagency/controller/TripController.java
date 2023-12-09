package com.example.travelagency.controller;

import com.example.travelagency.model.AirportModel;
import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.*;
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
    private final HotelService hotelService;

    @Autowired
    public TripController(TripService tripService, LocationService locationService, AirportService airportService, HotelService hotelService) {
        this.tripService = tripService;
        this.locationService = locationService;
        this.airportService = airportService;
        this.hotelService = hotelService;
    }

    @GetMapping()
    public String getTripList(Model model) {
        List<TripModel> tripList = tripService.getTripList();
        model.addAttribute("trips", tripList);
        return "trips";
    }

    @GetMapping("continent/{continent}")
    public String getTripListByContinent(@PathVariable(value = "continent") String continent, Model model) {
        List<TripModel> tripsByContinent = tripService.getTripsByContinent(continent);
        model.addAttribute("tripsByContinent", tripsByContinent);
        return "tripsByContinent";
        //todo nie dziala do widoku
    }

    @GetMapping("country/{country}")
    public String getTripListByCountry(@PathVariable(value = "country") String country, Model model) {
        List<TripModel> tripsByCountry = tripService.getTripsByCountry(country);
        model.addAttribute("tripsByCountry", tripsByCountry);
        return "redirect:trips/country/{country}";
        //todo nie dziala do widoku
    }


    @GetMapping("/addTrip")
    public String getAddTripForm(Model model) {
        TripModel tripModel = new TripModel();
        model.addAttribute("trip", tripModel);
        model.addAttribute("hotelModel", new HotelModel());
        model.addAttribute("airportModel", new AirportModel());
        model.addAttribute("foods", FoodOption.values());
        Set<String> continentList = locationService.getListOfContinents();
        model.addAttribute("continents", continentList);
        Set<String> countryList = locationService.getListOfCountires();
        model.addAttribute("countries", countryList);
        List<AirportModel> airportList = airportService.getAirportList();
        model.addAttribute("airports", airportList);
        List<HotelModel> hotelList = hotelService.getHotelsList();
        model.addAttribute("hotels", hotelList);
        Boolean promotion = tripModel.isPromoted();
        model.addAttribute("promotion", promotion);
//        Long duration = tripModel.getDuration();
//        model.addAttribute("duration",duration);
        Double priceAdult = tripModel.getPriceForAdult();
        model.addAttribute("priceAdult", priceAdult);
        Double priceChild = tripModel.getPriceForChild();
        model.addAttribute("priceChild", priceChild);

        return "addTrip";
    }

    @PostMapping("/addTrip")
    public String saveTrip(@ModelAttribute TripModel tripModel, Model model) {
//       model.addAttribute("tripModel", tripModel);
        tripService.PostAddTrip(tripModel);

        return "getTrips";
    }
//    @RequestMapping(value="/addTrip", method = RequestMethod.POST)
//    public String saveTrip(@ModelAttribute("tripModel") TripModel tripModel) {
//        tripService.PostAddTrip(tripModel);
//        return "getTrips";
//    }


}
