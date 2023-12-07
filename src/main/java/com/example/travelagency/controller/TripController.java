package com.example.travelagency.controller;

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

    private final FoodService foodService;

    private final HotelService hotelService;

    @Autowired
    public TripController(TripService tripService, LocationService locationService, AirportService airportService, FoodService foodService, HotelService hotelService) {
        this.tripService = tripService;
        this.locationService = locationService;
        this.airportService = airportService;
        this.foodService = foodService;
        this.hotelService = hotelService;
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
        model.addAttribute("hotelModel", new HotelModel());
        Set<String> continentList = locationService.getListOfContinents();
        model.addAttribute("continents", continentList);
        Set<String> countryList = locationService.getListOfCountires();
        model.addAttribute("countries", countryList);
        Set<String> airportList = airportService.getAirportModelNames();
        model.addAttribute("airports", airportList);
        List<HotelModel> hotelList = hotelService.getHotelsList();
        model.addAttribute("hotels", hotelList);
        List<String> foodList = tripService.getFoodOptions();
        model.addAttribute("foods", foodList);
        Boolean promotion = tripModel.isPromoted();
        model.addAttribute("promotion", promotion);
        Double priceAdult = tripModel.getPriceForAdult();
        model.addAttribute("priceAdult", priceAdult);
        Double priceChild = tripModel.getPriceForChild();
        model.addAttribute("priceChild", priceChild);

//        Set<Boolean> isPromoted  = tripService.isTripPromoted();
//        model.addAttribute("promotion",isPromoted);
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
