package com.example.travelagency.controller;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.AirportModel;
import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final LocationService locationService;
    private final AirportService airportService;
    private final HotelService hotelService;
    private final TripOrderService tripOrderService;

    @Autowired
    public TripController(TripService tripService, LocationService locationService, AirportService airportService, HotelService hotelService, TripOrderService tripOrderService) {
        this.tripService = tripService;
        this.locationService = locationService;
        this.airportService = airportService;
        this.hotelService = hotelService;
        this.tripOrderService = tripOrderService;
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
//        return "redirect:/trips/continent/{continent}";
        return "tripsByContinent";
    }

    @GetMapping("country/{country}")
    public String getTripListByCountry(@PathVariable(value = "country") String country, Model model) {
        List<TripModel> tripsByCountry = tripService.getTripsByCountry(country);
        model.addAttribute("tripsByCountry", tripsByCountry);
//        return "redirect:/trips/country/{country}";
        return "tripsByCountry";
    }

    @GetMapping("/add")
    public String getAddTripForm(Model model) {
        TripModel tripModel = new TripModel();
        model.addAttribute("trip", tripModel);
        model.addAttribute("hotelModel", new HotelModel());
        model.addAttribute("airportModel", new AirportModel());
        model.addAttribute("foods", FoodOption.values());
        List<AirportModel> airportList = airportService.getAirportList();
        model.addAttribute("airports", airportList);
        List<HotelModel> hotelList = hotelService.getHotelsList();
        model.addAttribute("hotels", hotelList);
        Boolean promotion = tripModel.isPromoted();
        model.addAttribute("promotion", promotion);
        Double priceAdult = tripModel.getPriceForAdult();
        model.addAttribute("priceAdult", priceAdult);
        Double priceChild = tripModel.getPriceForChild();
        model.addAttribute("priceChild", priceChild);
        return "addTrip";
    }


    @PostMapping("/add")
    public String saveTrip(@ModelAttribute TripModel tripModel, Model model) {
        tripService.PostAddTrip(tripModel);
        return "getTrips";
    }


    @GetMapping("/admin")
    public String getAdminTripList(Model model) {
        List<TripModel> tripList = tripService.getTripList();
        model.addAttribute("trips", tripList);
        return "adminTrips";
    }
    @GetMapping("/edit/{id}")
    public String getEditTripForm(@PathVariable(value = "id") Long id, Model model) {
        TripModel tripModel = tripService.getTripById(id);

        model.addAttribute("tripToEdit", tripModel);
//        model.addAttribute("hotelModel", tripModel.getHotel());
//        model.addAttribute("airportModel", tripModel.getAirportTo());
//        model.addAttribute("foods", tripModel.getFoodOption());

        model.addAttribute("allFoods", Arrays.asList(FoodOption.values()));
        model.addAttribute("allHotels",hotelService.getHotelsList());
        model.addAttribute("allAirports",airportService.getAirportList());


//        model.addAttribute("priceAdult", tripModel.getPriceForAdult());
//        model.addAttribute("priceChild", tripModel.getPriceForChild());
//        model.addAttribute("numberOfPlaces", tripModel.getNumberOfPlaces());
//        List<AirportModel> airportList = airportService.getAirportList();
//        model.addAttribute("airports", airportList);
//        List<HotelModel> hotelList = hotelService.getHotelsList();
//        model.addAttribute("hotels", hotelList);
        return "adminEditTrip";
    }

    @PostMapping("/edit/{id}")
    public String saveEditTrip(@ModelAttribute TripModel tripModel, Model model) {
        TripModel updatedTrip = tripService.saveEditTrip(tripModel);
//        return "redirect:/trips/admin";
        return "redirect:/trips/admin";
    }


    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") Long tripId) {
        if (!tripOrderService.getOrdersByTripId(tripId).isEmpty()) {
            throw new ApiRequestException("Trip can't be removed - trip orders exists");
        }
        tripService.deleteTrip(tripId);
        return "deleteInfo";
    }

    @GetMapping("/promoted")
    public String getPromotedTrips(Model model) {
        List<TripModel> promotedTrips = tripService.getPromotedTrips();
        model.addAttribute("promotedTrips", promotedTrips);
        return "promotedTrips";
    }


    @GetMapping("/lastMinute")
    public String getLastMinuteTrips(Model model) {
        List<TripModel> lastMinuteTrips = tripService.getLastMinuteTrips();
        model.addAttribute("lastMinuteTrips", lastMinuteTrips);
        return "lastMinute";
    }
}

